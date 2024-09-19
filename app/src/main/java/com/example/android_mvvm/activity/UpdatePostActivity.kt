package com.example.android_mvvm.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_mvvm.R
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePostActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etBody: EditText
    private lateinit var btnUpdate: Button
    private var postId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_post)

        etTitle = findViewById(R.id.et_title)
        etBody = findViewById(R.id.et_body)
        btnUpdate = findViewById(R.id.btn_update)

        // Intent orqali post ma'lumotlarini olish
        val post = intent.getParcelableExtra<Post>("post")

        post?.let {
            postId = it.id
            etTitle.setText(it.title)
            etBody.setText(it.body)
        }

        btnUpdate.setOnClickListener {
            updatePost()
        }
    }

    private fun updatePost() {
        val title = etTitle.text.toString()
        val body = etBody.text.toString()

        if (postId != -1) {
            val updatedPost = Post(postId, title, body)
            RetrofitHttp.postService.updatePost(postId, updatedPost).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        Log.d("updatePostNew",title)
                        Toast.makeText(this@UpdatePostActivity, "Post update successfully", Toast.LENGTH_SHORT).show()
                        finish() // Close activity and return to previous screen
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@UpdatePostActivity,"Null",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

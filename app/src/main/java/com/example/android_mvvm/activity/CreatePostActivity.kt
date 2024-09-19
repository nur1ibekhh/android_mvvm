package com.example.android_mvvm.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm.R
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.network.RetrofitHttp
import com.example.android_mvvm.viewModel.CreatViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etBody: EditText
    private lateinit var btnSave: Button
    private lateinit var createPostNew: CreatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        createPostNew = ViewModelProvider(this).get(CreatViewModel::class.java)
        etTitle = findViewById(R.id.et_title)
        etBody = findViewById(R.id.et_body)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val body = etBody.text.toString()
            if (title.isNotEmpty() && body.isNotEmpty()) {
                // Call createPost with context
                createPostNew.createPost(title, body, this)
                Log.d("creat",body!!)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
        createPostNew.creatView.observe(this, { post ->

        })
    }
}


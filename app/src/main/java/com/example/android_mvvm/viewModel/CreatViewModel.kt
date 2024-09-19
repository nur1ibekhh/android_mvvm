package com.example.android_mvvm.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.activity.CreatePostActivity
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatViewModel : ViewModel() {

    val creatView = MutableLiveData<Post>()

    fun createPost(title: String, body: String, context: Context) {
        val post = Post(0, title, body)

        RetrofitHttp.postService.createPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Log.d("Creatt", title)
                    Toast.makeText(context, "Post created successfully", Toast.LENGTH_SHORT).show()
                    creatView.value = response.body()
                } else {
                    Toast.makeText(context, "Failed to create post", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

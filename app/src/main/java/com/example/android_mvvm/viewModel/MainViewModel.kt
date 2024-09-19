package com.example.android_mvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    val allPosts =MutableLiveData<ArrayList<Post>>()
    val deletetPost = MutableLiveData<Post>()

         fun aipPostList(){
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                Log.d("MainActivity",response.body().toString())
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                allPosts.value = null
            }
        })
    }

     fun apiPostDelet(post: Post):LiveData<Post>{
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                deletetPost.value = response.body()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                deletetPost.value = null
            }
        })
        return deletetPost
    }
}
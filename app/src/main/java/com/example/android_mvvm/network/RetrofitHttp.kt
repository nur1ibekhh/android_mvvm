package com.example.android_mvvm.network

import com.example.android_mvvm.network.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {

    private val IS_TESTER = true
    private val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    private val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(server())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun server(): String {
        return if (IS_TESTER) SERVER_DEVELOPMENT else SERVER_PRODUCTION
    }

    val postService: PostService = retrofit.create(PostService::class.java)
}

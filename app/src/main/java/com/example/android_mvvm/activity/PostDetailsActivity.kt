package com.example.android_mvvm.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_mvvm.R
import com.example.android_mvvm.model.Post

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        tvTitle = findViewById(R.id.tv_title)
        tvBody = findViewById(R.id.tv_body)

        // Intent orqali post ma'lumotlarini olish
        val post = intent.getParcelableExtra<Post>("post")

        post?.let {
            tvTitle.text = it.title
            tvBody.text = it.body
        }
    }
}

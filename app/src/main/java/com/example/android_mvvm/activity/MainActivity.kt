package com.example.android_mvvm.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mvvm.R
import com.example.android_mvvm.adapter.PostAdapter
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    /*
    Basic Method
     */

    private fun initViews(){
        viewModel =ViewModelProvider(this).get(MainViewModel::class.java)

        var ll_thisPost = findViewById<LinearLayout>(R.id.ll_thisPost)
        ll_thisPost.setOnClickListener {

           // openDetailPage()
        }
        var ll_swipe = findViewById<LinearLayout>(R.id.ll_swipe)
        ll_swipe.setOnClickListener {
           // oppenUpdatePostActivity()
        }
        var btn_update = findViewById<Button>(R.id.btn_update)
        btn_update.setOnClickListener {
            openCreatePostActivity()

        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(this,1))
        viewModel.aipPostList()
        viewModel.allPosts.observe(this,{
            refreshAdapter(it)
        })

    }

    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this,posts)
        recyclerView.adapter = adapter
    }

    // Classlarni chaqirish
    private fun oppenUpdatePostActivity(){
        val intent = Intent(this, UpdatePostActivity::class.java)
        startActivity(intent)
    }
    private fun openDetailPage(){
        val intent = Intent(this, PostDetailsActivity::class.java)
        startActivity(intent)
    }
    private fun openCreatePostActivity() {
        val intent = Intent(this, CreatePostActivity::class.java)
        startActivity(intent)
    }
}
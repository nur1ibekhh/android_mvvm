package com.example.android_mvvm.adapter
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mvvm.R
import com.example.android_mvvm.activity.MainActivity
import com.example.android_mvvm.activity.PostDetailsActivity
import com.example.android_mvvm.model.Post
import com.example.android_mvvm.utils.Utils

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return PostViewHolder(view)
    }
// darsdagi- topshiriq uchun
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]
        if (holder is PostViewHolder) {
            val ll_post = holder.ll_post
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body

            tv_title.text = post.title
            tv_body.text = post.body

            ll_post.setOnClickListener {
                deletePostDialog(post)
            }
        }
    }
    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "O'chirishni xohlaysizmi? "

        Utils.customDialog(activity,title,body,object : Utils.DialogListener{
            override fun onPositiveClick() {
                activity.viewModel.apiPostDelet(post).observe(activity,{
                    Log.d("Clear",post.id.toString() +"lik post o'chirildi")
                    activity.viewModel.aipPostList()
                })
            }
            override fun onNegativeClick() {

            }
        })
    }

    inner class PostViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var ll_post: LinearLayout = view.findViewById(R.id.ll_post)
        var tv_title: TextView = view.findViewById(R.id.tv_title)
        var tv_body: TextView = view.findViewById(R.id.tv_body)
    }

    // 3- topshiriq uchun
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val post: Post = items[position]
//        if (holder is PostViewHolder) {
//            val ll_post = holder.ll_post
//            val tv_title = holder.tv_title
//            val tv_body = holder.tv_body
//
//            ll_post.setOnClickListener {
//                val intent = Intent(activity, PostDetailsActivity::class.java)
//                intent.putExtra("post", post)
//                activity.startActivity(intent)
//            }
//
//            tv_title.text = post.title
//            tv_body.text = post.body
//        }
//    }

    // 1- topshiriq uchun

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val post: Post = items[position]
//        if (holder is PostViewHolder) {
//            val ll_post = holder.ll_post
//            val tv_title = holder.tv_title
//            val tv_body = holder.tv_body
//
//            tv_title.text = post.title
//            tv_body.text = post.body
//
//            ll_post.setOnClickListener {
//                activity.openUpdatePostActivity(post)
//            }
//        }
//    }
}

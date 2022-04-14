package com.kaimiya.gaurav.fidoointerviewtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kaimiya.gaurav.fidoointerviewtask.R
import com.kaimiya.gaurav.fidoointerviewtask.model.Post

class PostRecAdapter: RecyclerView.Adapter<PostRecAdapter.PostViewHolder>() {


    var posts = ArrayList<Post>()

    fun updatePosts(posts: List<Post>){
        this.posts = posts as ArrayList<Post>
    }

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.text_view_title)
        val body = view.findViewById<TextView>(R.id.text_view_body)
        val userId = view.findViewById<TextView>(R.id.text_view_userid)

        fun bind(post: Post){
            title.text = post.title
            userId.text = post.user_id.toString()
            body.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
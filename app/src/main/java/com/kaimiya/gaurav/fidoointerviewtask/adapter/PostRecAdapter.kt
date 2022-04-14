package com.kaimiya.gaurav.fidoointerviewtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kaimiya.gaurav.fidoointerviewtask.R
import com.kaimiya.gaurav.fidoointerviewtask.databinding.PostListItemBinding
import com.kaimiya.gaurav.fidoointerviewtask.model.Post

class PostRecAdapter: RecyclerView.Adapter<PostRecAdapter.PostViewHolder>() {


    private val diffCallback= object: DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var posts: List<Post>
        get() = differ.currentList
        set(value) {differ.submitList(value)}


    class PostViewHolder(val binding: PostListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            val post = posts[position]
            textViewTitle.text = post.title
            textViewUserid.text = post.user_id.toString()
            textViewBody.text = post.body

        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
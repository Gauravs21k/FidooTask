package com.kaimiya.gaurav.fidoointerviewtask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaimiya.gaurav.fidoointerviewtask.adapter.PostRecAdapter
import com.kaimiya.gaurav.fidoointerviewtask.databinding.ActivityMainBinding
import com.kaimiya.gaurav.fidoointerviewtask.network.RetrofitInstance
import com.kaimiya.gaurav.fidoointerviewtask.repository.MainRepository
import com.kaimiya.gaurav.fidoointerviewtask.viewmodel.MainActivityViewModel
import com.kaimiya.gaurav.fidoointerviewtask.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    //private val postRecAdapter = PostRecAdapter()
    lateinit var binding: ActivityMainBinding
    lateinit var postRecAdapter: PostRecAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = RetrofitInstance.api
        val mainRepository = MainRepository(api)

        setRecyclerView()

        /*binding.recyclerview.adapter = postRecAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        */
        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainActivityViewModel::class.java)


        viewModel.posts.observe(this) {
            postRecAdapter.updatePosts(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.loadingView.visibility = View.VISIBLE
            } else {
                binding.loadingView.visibility = View.GONE
            }
        })

        viewModel.getPostList()

    }

    private fun setRecyclerView() = binding.recyclerview.apply {
        postRecAdapter = PostRecAdapter()
        adapter = postRecAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}

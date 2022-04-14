package com.kaimiya.gaurav.fidoointerviewtask.repository

import com.kaimiya.gaurav.fidoointerviewtask.network.PostApi

class MainRepository constructor(private val api: PostApi) {

    suspend fun getPostList() = api.getPostList()

}
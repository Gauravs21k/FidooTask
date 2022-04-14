package com.kaimiya.gaurav.fidoointerviewtask.network

import com.kaimiya.gaurav.fidoointerviewtask.model.PostList
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPostList(): Response<PostList>
}
package com.kaimiya.gaurav.fidoointerviewtask.network

import com.kaimiya.gaurav.fidoointerviewtask.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("/public/v1/posts")
    suspend fun getDataFromApi(): Response<List<Post>>
}
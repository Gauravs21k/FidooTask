package com.kaimiya.gaurav.fidoointerviewtask.network

import com.kaimiya.gaurav.fidoointerviewtask.logginginterceptor.AddLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: PostApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://gorest.co.in/public/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(AddLoggingInterceptor.setLogging())
            .build()
            .create(PostApi::class.java)
    }
}
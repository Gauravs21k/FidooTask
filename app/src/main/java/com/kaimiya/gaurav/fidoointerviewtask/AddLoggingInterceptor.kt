package com.kaimiya.gaurav.fidoointerviewtask

import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor


object AddLoggingInterceptor {
    fun setLogging(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
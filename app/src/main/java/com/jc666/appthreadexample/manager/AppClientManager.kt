package com.jc666.appthreadexample.manager

import com.jc666.appthreadexample.config.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppClientManager  private constructor() {
    private val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient().newBuilder()
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = AppClientManager()
        val client: Retrofit
            get() = manager.retrofit
    }
}
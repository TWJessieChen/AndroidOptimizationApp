package com.jc666.appthreadexample.api

import com.jc666.appthreadexample.data.TodosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Call<TodosResponse>

}
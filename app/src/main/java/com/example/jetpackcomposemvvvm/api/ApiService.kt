package com.example.jetpackcomposemvvvm.api

import com.example.jetpackcomposemvvvm.pojo.MResponse
import com.example.jetpackcomposemvvvm.pojo.Post
import retrofit2.http.GET

interface ApiService {
    companion object{
//        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val BASE_URL = "https://reqres.in/api/"
    }

    @GET("users")
    suspend fun getPost():MResponse
}
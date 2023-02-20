package com.example.jetpackcomposemvvvm.repository

import com.example.jetpackcomposemvvvm.api.ApiService
import com.example.jetpackcomposemvvvm.pojo.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost():Flow<List<Post>> = flow {
        emit(apiService.getPost().data)
    }.flowOn(Dispatchers.IO)
}
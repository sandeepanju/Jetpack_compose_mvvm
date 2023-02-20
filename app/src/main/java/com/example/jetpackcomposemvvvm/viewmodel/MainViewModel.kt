package com.example.jetpackcomposemvvvm.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposemvvvm.pojo.ApiState
import com.example.jetpackcomposemvvvm.pojo.Post
import com.example.jetpackcomposemvvvm.pojo.ViewState
import com.example.jetpackcomposemvvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val postResponse: State<ViewState<List<Post>>> get() = _postResponse
    private val _postResponse: MutableState<ViewState<List<Post>>> = mutableStateOf(ViewState.Ideal)
    fun getPostList() {
        viewModelScope.launch {
            repository.getPost()
                .onStart {
                    _postResponse.value = ViewState.Loading
                }
                .catch {
                    _postResponse.value = ViewState.Error(this.toString())
                }
                .collect {
                    _postResponse.value = ViewState.Success(it)
                }
        }
    }


val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
//        getPostList()
//        getPost()
    }

    fun getPost() = viewModelScope.launch {
        repository.getPost()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Failure(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
    }
}
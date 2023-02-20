package com.example.jetpackcomposemvvvm.pojo

sealed class ViewState<out T>{
    object Ideal : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    object NetworkError : ViewState<Nothing>()
    data class Success<T>(val data : T) : ViewState<T>()
    data class Error(val data : String) : ViewState<Nothing>()
}
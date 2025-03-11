package com.simple.fetchrewardcompose.data.network

import com.simple.fetchrewardcompose.FetchResult

sealed class NetworkResult<T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Failure<T>(val errorMessage: String): NetworkResult<T>()
}

fun <T> NetworkResult<T>.toResult() = when(this){
    is NetworkResult.Failure -> FetchResult.Failure(this.errorMessage)
    is NetworkResult.Success -> FetchResult.Success(this.data)
}
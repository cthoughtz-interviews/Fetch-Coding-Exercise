package com.simple.fetchrewardcompose

sealed class FetchResult<out T> {
    data object Loading : FetchResult<Nothing>()
    data class Success<T>(val data: T) : FetchResult<T>()
    data class Failure<T>(val errorMessage: String) : FetchResult<T>()
    data object None: FetchResult<Nothing>()
}
package com.example.domain_models.network

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Failure(val throwable: Throwable) : DataResult<Nothing>()
}

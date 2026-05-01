package com.swg.realestate.domain.util

sealed class ResponseResult<out T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(
        val error: AppError,
        val throwable: Throwable? = null
    ) : ResponseResult<Nothing>()

    data object Loading : ResponseResult<Nothing>()
}
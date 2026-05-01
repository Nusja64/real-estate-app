package com.swg.realestate.domain.util

sealed class AppError {
    data object NoInternet : AppError()
    data object Server : AppError()
    data class Unknown(val message: String?) : AppError()
}


fun AppError.toMessage(): String =
    when (this) {
        AppError.NoInternet -> "No internet connection"
        AppError.Server -> "Server error"
        is AppError.Unknown -> "Unknown error"
    }

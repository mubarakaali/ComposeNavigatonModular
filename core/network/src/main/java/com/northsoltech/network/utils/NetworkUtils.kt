package com.northsoltech.network.utils

import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


    private const val genericNetworkError = "An error occurred getting data from server."

    fun getNetworkErrorMessage(e: Exception) = when (e) {
        is IOException -> "Something went wrong."
        is SocketTimeoutException -> "Request timed out. Try again."
        is UnknownHostException -> "Unable to connect to server."
        else -> genericNetworkError
    }

    fun getErrorMessage(httpCode: Int) = when (httpCode) {
        // User unauthorised error
        401 -> "You have been unauthorized."
        404 -> "Not Found"
        204 -> "User Not exist"
        // Time out error
        408 -> "Request timed out. Try again."
        // Internal server error
        500 -> "A server error occurred."
        // Any other error executing the API
        else -> genericNetworkError
}
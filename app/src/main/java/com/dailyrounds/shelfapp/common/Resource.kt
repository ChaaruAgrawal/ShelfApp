package com.dailyrounds.shelfapp.common

/**
 * Maintain states for data retrieved from APIs
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T, message: String? = null) : Resource<T>(data, message = message)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Initialized<T>(data: T? = null) : Resource<T>(data)
}

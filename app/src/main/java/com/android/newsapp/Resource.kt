package com.android.newsapp

sealed class Resource<out T> {
    data class ResourceSuccess<out T>(val data: T) : Resource<T>()
    data class ResourceError<out T>(val error: Throwable) : Resource<T>()
    data class ResourceLoading<out T>(val data: T? = null) : Resource<T>()
}
package com.android.newsapp.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsAuthenticator @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("X-Api-Key", "0c75fdf2d9ab4fc1862ff8d83097eda2")
        return chain.proceed(builder.build())
    }

}

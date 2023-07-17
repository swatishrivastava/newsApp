package com.android.newsapp.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsAuthenticator @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("X-Api-Key", "14a8301e38c74ec18ba091acdb18f32a")
        return chain.proceed(builder.build())
    }

}

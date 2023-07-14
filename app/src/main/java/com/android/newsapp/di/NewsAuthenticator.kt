package com.android.newsapp.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsAuthenticator @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("X-Api-Key", "12ecd7e071af4836a6603720495c61b9")
        return chain.proceed(builder.build())
    }

}

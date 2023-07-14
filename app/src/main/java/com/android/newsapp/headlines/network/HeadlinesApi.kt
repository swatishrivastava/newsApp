package com.android.newsapp.headlines.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlinesApi {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(@Query("sources") sources: String): Response<HeadlinesResponse>
}
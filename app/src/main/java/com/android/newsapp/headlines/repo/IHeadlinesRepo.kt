package com.android.newsapp.headlines.repo

import com.android.newsapp.headlines.network.HeadlinesResponse
import retrofit2.Response

interface IHeadlinesRepo {
    suspend fun getHeadlines(sources: String):Response<HeadlinesResponse>
}
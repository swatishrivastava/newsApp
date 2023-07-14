package com.android.newsapp.headlines.repo

import com.android.newsapp.headlines.network.HeadlinesApi
import com.android.newsapp.headlines.network.HeadlinesResponse
import retrofit2.Response
import javax.inject.Inject

class HeadlinesRepo @Inject constructor(private val api: HeadlinesApi) : IHeadlinesRepo {
    override suspend fun getHeadlines(sources: String): Response<HeadlinesResponse> {
        return api.getHeadlines(sources)
    }
}
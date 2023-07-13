package com.android.newsapp.sources.repo

import com.android.newsapp.sources.network.SourceApi
import com.android.newsapp.sources.network.SourcesResponse
import retrofit2.Response
import javax.inject.Inject

class SourceRepo @Inject constructor(private val api: SourceApi) : ISourceRepo {
    override suspend fun getAllSources(): Response<SourcesResponse> {
        return api.getAllNewsSources()
    }
}
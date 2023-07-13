package com.android.newsapp.sources.repo

import com.android.newsapp.sources.network.SourcesResponse
import retrofit2.Response

interface ISourceRepo {
    suspend fun getAllSources(): Response<SourcesResponse>
}
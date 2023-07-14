package com.android.newsapp.sources.network


data class SourcesResponse(
    val sources: List<Source>,
    val status: String
)

data class Source(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String
)
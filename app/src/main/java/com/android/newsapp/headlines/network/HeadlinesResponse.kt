package com.android.newsapp.headlines.network

data class HeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
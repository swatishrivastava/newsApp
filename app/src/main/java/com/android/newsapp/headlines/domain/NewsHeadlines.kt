package com.android.newsapp.headlines.domain

import java.util.UUID

data class NewsHeadlines(
    val id: String = UUID.randomUUID().toString(),
    val title: String? = "",
    val description: String? = "",
    val author: String? = "",
    val pic: String? = "",
    val url: String? = ""
)

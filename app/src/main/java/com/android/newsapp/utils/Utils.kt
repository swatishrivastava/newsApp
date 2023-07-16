package com.android.newsapp.utils

import com.android.newsapp.headlines.NewsHeadlines
import com.android.newsapp.saved.repo.News

fun getNewsFromNewsHeadlines(newsHeadlines: NewsHeadlines): News {
    return News(
        newsHeadlines.id,
        newsHeadlines.title ?: "",
        newsHeadlines.description ?: "",
        newsHeadlines.author ?: "",
        newsHeadlines.pic ?: "",
        newsHeadlines.url ?: ""
    )
}
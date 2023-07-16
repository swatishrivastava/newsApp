package com.android.newsapp.saved.repo

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsDao: NewsDao) {
    val savedNews: Flow<List<News>> = newsDao.getAll()

    @WorkerThread
    suspend fun insert(news: News) {
        newsDao.insertNews(news)
    }

    suspend fun delete(news: News) {
        newsDao.delete(news)
    }
}
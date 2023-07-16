package com.android.newsapp.saved.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.newsapp.headlines.NewsHeadlines
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_info")
     fun getAll(): Flow<List<News>>

    @Insert
     suspend fun insertNews(news: News)

    @Delete
     suspend fun delete(news: News)
}
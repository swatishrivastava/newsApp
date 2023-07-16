package com.android.newsapp.di

import android.app.Application
import androidx.room.Room
import com.android.newsapp.saved.repo.NewsDao
import com.android.newsapp.saved.repo.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, "news-database")
            .build()
    }

    @Provides
    fun provideUserDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }
}
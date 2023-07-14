package com.android.newsapp.di

import com.android.newsapp.headlines.repo.HeadlinesRepo
import com.android.newsapp.headlines.repo.IHeadlinesRepo
import com.android.newsapp.sources.repo.ISourceRepo
import com.android.newsapp.sources.repo.SourceRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun headlinesRepo(headlinesRepo: HeadlinesRepo) : IHeadlinesRepo

    @Binds
    abstract fun sourcesRepo(sourceRepo: SourceRepo) : ISourceRepo
}
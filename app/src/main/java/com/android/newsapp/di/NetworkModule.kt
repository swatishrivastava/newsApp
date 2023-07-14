package com.android.newsapp.di

import com.android.newsapp.sources.network.SourceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideBaseUrl() = "https://newsapi.org/"

    @Singleton
    @Provides
    fun provideOkHttpClient(newsAuthenticator: NewsAuthenticator) :OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(newsAuthenticator)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://newsapi.org/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideSourceApiService(retrofit: Retrofit): SourceApi =
        retrofit.create(SourceApi::class.java)

}
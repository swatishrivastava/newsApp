package com.android.newsapp.headlines.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.Resource
import com.android.newsapp.headlines.domain.NewsHeadlines
import com.android.newsapp.headlines.network.Article
import com.android.newsapp.headlines.repo.IHeadlinesRepo
import com.android.newsapp.saved.repo.News
import com.android.newsapp.saved.repo.NewsDao
import com.android.newsapp.utils.DEFAULT_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val repo: IHeadlinesRepo,
    private val newsDao: NewsDao
) : ViewModel() {
    private val _headlinesLiveData: MutableLiveData<Resource<List<NewsHeadlines>>> =
        MutableLiveData()
    val headlinesLiveData: LiveData<Resource<List<NewsHeadlines>>> = _headlinesLiveData
    fun getHeadlines(sources: String) {
        _headlinesLiveData.value = Resource.ResourceLoading()
        viewModelScope.launch {
            val response = repo.getHeadlines(getNewsSource(sources))
            if (response.isSuccessful) {
                response.body()?.let {
                    _headlinesLiveData.value =
                        Resource.ResourceSuccess(getNewsHeadlines(response.body()!!.articles))
                }
            } else {
                _headlinesLiveData.value =
                    Resource.ResourceError(Exception(response.message()))
            }
        }
    }

    private fun getNewsSource(sources: String): String {
        var newsSource = ""
        sources.ifEmpty {
            DEFAULT_SOURCE
        }.also { newsSource = it }
        return newsSource
    }

    private fun getNewsHeadlines(articles: List<Article>): List<NewsHeadlines> {
        val listOfheadlines = mutableListOf<NewsHeadlines>()
        articles.forEach {
            listOfheadlines.add(
                NewsHeadlines(
                    title = it.title,
                    description = it.description,
                    author = it.author,
                    pic = it.urlToImage,
                    url = it.url
                )
            )
        }
        return listOfheadlines
    }

    fun saveNewsToReadLater(news: News) {
        viewModelScope.launch {
            newsDao.insertNews(news)
        }
    }
}
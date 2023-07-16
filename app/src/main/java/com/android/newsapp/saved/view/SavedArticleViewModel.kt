package com.android.newsapp.saved.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.newsapp.headlines.domain.NewsHeadlines
import com.android.newsapp.saved.repo.News
import com.android.newsapp.saved.repo.NewsRepository
import com.android.newsapp.utils.getNewsFromNewsHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SavedArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val savedHeadlinesLiveData: LiveData<List<News>> = newsRepository.savedNews.asLiveData()

    fun deleteNews(newsHeadlines: NewsHeadlines) {
        val news = getNewsFromNewsHeadlines(newsHeadlines)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                newsRepository.delete(news)
            }
        }
    }


}
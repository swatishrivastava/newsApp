package com.android.newsapp.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.android.newsapp.Resource
import com.android.newsapp.headlines.NewsHeadlines
import com.android.newsapp.headlines.network.Article
import com.android.newsapp.saved.repo.News
import com.android.newsapp.saved.repo.NewsDao
import com.android.newsapp.saved.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SavedArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val savedHeadlinesLiveData: LiveData<List<News>> = newsRepository.savedNews.asLiveData()



}
package com.android.newsapp.headlines.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.Resource
import com.android.newsapp.headlines.NewsHeadlines
import com.android.newsapp.headlines.network.Article
import com.android.newsapp.headlines.repo.IHeadlinesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(private val repo: IHeadlinesRepo) : ViewModel() {

    private val _headlinesLiveData: MutableLiveData<Resource<List<NewsHeadlines>>> =
        MutableLiveData()
    val headlinesLiveData: LiveData<Resource<List<NewsHeadlines>>> = _headlinesLiveData
    fun getHeadlines(sources: String) {
        viewModelScope.launch {
            val response = repo.getHeadlines(sources)
            if (response.isSuccessful) {
                response.body()?.let {
                    _headlinesLiveData.value =
                        Resource.ResourceSuccess(getNewsHeadlines(response.body()!!.articles))
                }
            } else {
                _headlinesLiveData.value =
                    Resource.ResourceError(Exception("Unable to get sources"))
            }
        }
    }


    private fun getNewsHeadlines(articles: List<Article>): List<NewsHeadlines> {
        val listOfheadlines = mutableListOf<NewsHeadlines>()
        articles.forEach {
            listOfheadlines.add(NewsHeadlines(it.title, it.description, it.author, it.urlToImage))
        }
        return listOfheadlines
    }
}
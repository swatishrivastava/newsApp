package com.android.newsapp.sources

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.Resource
import com.android.newsapp.sources.domain.NewsSources
import com.android.newsapp.sources.network.Source
import com.android.newsapp.sources.repo.SourceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val repo: SourceRepo) : ViewModel() {

    private val _sourceLiveData: MutableLiveData<Resource<List<NewsSources>>> = MutableLiveData()
    val sourceLiveData: LiveData<Resource<List<NewsSources>>> = _sourceLiveData
    fun getSources() {
        Log.d("test", " I am inside source viewmodel class  ")
        viewModelScope.launch {
            val res = repo.getAllSources()
            if (res.isSuccessful) {
                res.body()?.let {
                    _sourceLiveData.value =
                        Resource.ResourceSuccess(getNewsSources(res.body()!!.sources))
                }
            } else {
                _sourceLiveData.value = Resource.ResourceError(Exception("Unable to get sources"))
            }
        }
    }

    private fun getNewsSources(list: List<Source>): List<NewsSources> {
        val listOfNewSources = mutableListOf<NewsSources>()
        list.forEach {
            listOfNewSources.add(NewsSources(it.id, it.name))
        }
        return listOfNewSources
    }
}
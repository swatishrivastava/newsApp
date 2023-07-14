package com.android.newsapp.headlines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.newsapp.R
import com.android.newsapp.Resource
import com.android.newsapp.headlines.views.HeadlinesViewModel
import com.android.newsapp.sources.views.SourcesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeadlines("bbc-news")
        viewModel.headlinesLiveData.observe(this) {
            when (it) {
                is Resource.ResourceSuccess -> {

                }
                is Resource.ResourceError -> {

                }
                is Resource.ResourceLoading -> {

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

}
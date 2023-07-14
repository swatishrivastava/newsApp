package com.android.newsapp.sources.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.newsapp.R
import com.android.newsapp.Resource
import com.android.newsapp.sources.domain.NewsSources
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceFragment : Fragment() {

    private val viewModel: SourcesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test", "I am inside source fragement")
        viewModel.getSources()
        viewModel.sourceLiveData.observe(this) {
            handleUI(it)
        }
    }

    private fun handleUI(it: Resource<List<NewsSources>>?) {
        when (it) {
            is Resource.ResourceSuccess -> {

            }

            is Resource.ResourceError -> {

            }

            is Resource.ResourceLoading -> {

            }

            else -> {}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source, container, false)
    }
}
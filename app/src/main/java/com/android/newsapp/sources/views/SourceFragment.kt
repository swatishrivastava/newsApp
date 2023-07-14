package com.android.newsapp.sources.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.R
import com.android.newsapp.Resource
import com.android.newsapp.sources.domain.NewsSources
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SourceFragment : Fragment() {

    private val viewModel: SourcesViewModel by viewModels()
    private var adapter = SourceAdapter(emptyList())
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
                adapter = SourceAdapter(it.data)
                adapter.notifyDataSetChanged()
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
        val view = inflater.inflate(R.layout.fragment_source, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.sources_list)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        return view
    }
}
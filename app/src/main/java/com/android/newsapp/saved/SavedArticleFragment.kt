package com.android.newsapp.saved

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsapp.R
import com.android.newsapp.Resource
import com.android.newsapp.headlines.NewsHeadlines
import com.android.newsapp.headlines.views.HeadlinesAdapter
import com.android.newsapp.headlines.views.HeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_headlines.headlines_list


@AndroidEntryPoint
class SavedArticleFragment : Fragment() {
    private val viewModel: SavedArticleViewModel by viewModels()
    private lateinit var adapter: HeadlinesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.savedHeadlinesLiveData.observe(this) {
            val list = mutableListOf<NewsHeadlines>()
            it.forEach {
                list.add(
                    NewsHeadlines(
                        it.title,
                        it.description,
                        it.author,
                        it.pic,
                        it.url
                    )
                )
            }
            adapter = HeadlinesAdapter(requireContext(), list)
            headlines_list.adapter = adapter

        }
    }

    private fun initializeList() {
        with(headlines_list) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
        }
    }

}
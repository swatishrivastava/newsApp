package com.android.newsapp.headlines

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsapp.R
import com.android.newsapp.Resource
import com.android.newsapp.SELECTED_SOURCES
import com.android.newsapp.headlines.views.HeadlinesAdapter
import com.android.newsapp.headlines.views.HeadlinesDetailsActivity
import com.android.newsapp.headlines.views.HeadlinesViewModel
import com.android.newsapp.saved.repo.News
import com.android.newsapp.sources.views.SourceAdapter
import com.android.newsapp.sources.views.SourcesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_headlines.headlines_list
import kotlinx.android.synthetic.main.fragment_source.sources_list
import kotlin.random.Random


@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var arrOfSources: MutableSet<String>
    private var sourcesStr: String = ""
    private lateinit var adapter: HeadlinesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        arrOfSources = sharedPref.getStringSet(SELECTED_SOURCES, emptySet())!!
        if (arrOfSources.isEmpty()) {
            sourcesStr = "bbc-news"
        } else {
            arrOfSources.forEach {
                sourcesStr = "$sourcesStr,$it"
            }
        }
        viewModel.getHeadlines(sourcesStr)
        viewModel.headlinesLiveData.observe(this) {
            when (it) {
                is Resource.ResourceSuccess -> {
                    adapter = HeadlinesAdapter(requireContext(), it.data)
                    headlines_list.adapter = adapter
                    handleHeadlineClick()
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()

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

    private fun handleHeadlineClick() {
        adapter.setOnClickListener(object :
            HeadlinesAdapter.OnClickListener {
            override fun onClick(url: String) {
                val bundle = Bundle()
                bundle.putString("headlineUrl", url)
                val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
            }

            override fun saveLater(news: NewsHeadlines) {
                viewModel.saveNewsToReadLater(
                    News(
                        Random.nextInt(),
                        news.title,
                        news.description,
                        news.author,
                        news.pic,
                        news.url
                    )
                )
            }
        })
    }
}
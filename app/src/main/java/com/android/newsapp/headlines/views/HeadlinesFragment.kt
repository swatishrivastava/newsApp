package com.android.newsapp.headlines.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.NewsActivity
import com.android.newsapp.R
import com.android.newsapp.utils.Resource
import com.android.newsapp.headlines.domain.NewsHeadlines
import com.android.newsapp.utils.HEADLINE_URL
import com.android.newsapp.utils.getNewsFromNewsHeadlines
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment_layout.news_progressBar

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModels()
    private lateinit var headlinesAdapter: HeadlinesAdapter
    private lateinit var errorTextview: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment_layout, container, false)
        val recyclerView = view.rootView.findViewById<RecyclerView>(R.id.news_list)
        errorTextview = view.rootView.findViewById(R.id.error_textView)
        initializeList(recyclerView)
        handleHeadlineClick()
        return view
    }


    override fun onStart() {
        super.onStart()
        viewModel.getHeadlines(getSelectedSources())
        viewModel.headlinesLiveData.observe(this) {
            when (it) {
                is Resource.ResourceSuccess -> {

                    updateSuccessUi(it)
                }

                is Resource.ResourceError -> {
                    updateErrorUi()
                }

                is Resource.ResourceLoading -> {
                    news_progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun updateSuccessUi(it: Resource.ResourceSuccess<List<NewsHeadlines>>) {
        news_progressBar.visibility = View.GONE
        if (it.data.isEmpty()) {
            errorTextview.visibility = View.VISIBLE
            errorTextview.text = getString(R.string.no_news)
        } else {
            headlinesAdapter.updateHeadlines(it.data)
        }

    }

    private fun updateErrorUi() {
        news_progressBar.visibility = View.GONE
        errorTextview.visibility = View.VISIBLE
    }


    private fun getSelectedSources(): String {
        var sourceStr = ""
        (activity as NewsActivity).sourceArray.forEach {
            sourceStr = "$sourceStr,$it"
        }
        return sourceStr
    }


    private fun initializeList(headlines_list: RecyclerView) {
        with(headlines_list) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
            headlinesAdapter = HeadlinesAdapter(requireContext(), emptyList())
            adapter = headlinesAdapter
        }
    }

    private fun handleHeadlineClick() {
        headlinesAdapter.setOnClickListener(object :
            HeadlinesAdapter.OnClickListener {
            override fun onClick(url: String) {
                val bundle = Bundle()
                bundle.putString(HEADLINE_URL, url)
                val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
            }

            override fun saveLater(news: NewsHeadlines) {
                Toast.makeText(activity, getString(R.string.read_article_later), Toast.LENGTH_LONG)
                    .show()
                viewModel.saveNewsToReadLater(
                    getNewsFromNewsHeadlines(news)
                )
            }
        })
    }
}
package com.android.newsapp.saved.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsapp.HEADLINE_URL
import com.android.newsapp.R
import com.android.newsapp.headlines.NewsHeadlines
import com.android.newsapp.headlines.views.HeadlinesDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment_layout.news_list


@AndroidEntryPoint
class SavedArticleFragment : Fragment() {
    private val viewModel: SavedArticleViewModel by viewModels()
    private lateinit var savedadapter: SavedArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment_layout, container, false)
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
            savedadapter = SavedArticleAdapter(requireContext(), list)
            news_list.adapter = savedadapter
            handleNewsClick()

        }
    }

    private fun initializeList() {
        with(news_list) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
        }
    }

    private fun handleNewsClick() {
        savedadapter.setOnClickListener(object :
            SavedArticleAdapter.OnClickListener {
            override fun onClick(url: String) {
                val bundle = Bundle()
                bundle.putString(HEADLINE_URL, url)
                val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
            }

            override fun onDeleteArticle(news: NewsHeadlines) {
                viewModel.deleteNews(news)
            }


        })
    }

}
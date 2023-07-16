package com.android.newsapp.sources.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsapp.R
import com.android.newsapp.Resource
import com.android.newsapp.SELECTED_SOURCES
import com.android.newsapp.sources.domain.NewsSources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_source.sources_list


@AndroidEntryPoint
class SourceFragment : Fragment() {
    private val viewModel: SourcesViewModel by viewModels()
    private lateinit var adapter: SourceAdapter
    private lateinit var sharedPref: SharedPreferences
    private lateinit var arrOfSources:MutableSet<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        arrOfSources = sharedPref.getStringSet(SELECTED_SOURCES, emptySet())!!
        viewModel.getSources()
        viewModel.sourceLiveData.observe(this) {
            handleUI(it)
        }
    }

    private fun handleUI(it: Resource<List<NewsSources>>?) {
        when (it) {
            is Resource.ResourceSuccess -> {
                adapter = SourceAdapter(it.data, arrOfSources)
                sources_list.adapter = adapter
                handleSourceClick()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()

    }

    private fun handleSourceClick() {
        val newList = mutableListOf<String>()
        newList.addAll(arrOfSources.toSet())
        adapter.setOnClickListener(object :
            SourceAdapter.OnClickListener {
            override fun onClick(sourceId: String, isSelected: Boolean) {
                if (isSelected) {
                    newList.add(sourceId)
                } else {
                    newList.remove(sourceId)
                }
                with(sharedPref.edit()) {
                    this?.putStringSet(SELECTED_SOURCES, newList.toSet())
                    this?.apply()
                }
            }
        })
    }

    private fun initializeList() {
        with(sources_list) {
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
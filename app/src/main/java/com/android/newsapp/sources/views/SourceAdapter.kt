package com.android.newsapp.sources.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.R
import com.android.newsapp.sources.domain.NewsSources

class SourceAdapter(private val listOfSources: List<NewsSources>) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val source: CheckBox
        init {
            source = view.findViewById(R.id.source_checkbox)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.source_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfSources.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source.text = listOfSources[position].name
    }
}
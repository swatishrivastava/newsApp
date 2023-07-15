package com.android.newsapp.sources.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.R
import com.android.newsapp.databinding.SourceListItemBinding
import com.android.newsapp.sources.domain.NewsSources

class SourceAdapter(
    private val listOfSources: List<NewsSources>,
    private val arrOfSources: MutableSet<String>?
) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = SourceListItemBinding.bind(view)
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
        with(holder.binding) {
            sourceCheckbox.text = listOfSources[position].name
            sourceCheckbox.isChecked = arrOfSources?.contains(listOfSources[position].id) == true
            sourceCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                onClickListener?.onClick(listOfSources[position].id, isChecked)
            }
        }
    }

    interface OnClickListener {
        fun onClick(sourceId: String, isSelected: Boolean)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}


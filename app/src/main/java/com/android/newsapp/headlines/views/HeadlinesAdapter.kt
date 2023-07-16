package com.android.newsapp.headlines.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.R
import com.android.newsapp.databinding.HeadlinesListItemBinding
import com.android.newsapp.headlines.NewsHeadlines
import com.bumptech.glide.Glide

class HeadlinesAdapter(
    private val context: Context,
    private var listOfHeadlines: List<NewsHeadlines>,
) : RecyclerView.Adapter<HeadlinesAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = HeadlinesListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.headlines_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfHeadlines.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            headlineLayout.setOnClickListener { onClickListener?.onClick(listOfHeadlines[position].url?:"") }
            articleTitle.text = listOfHeadlines[position].title
            articleDescription.text = listOfHeadlines[position].description
            articleAuthor.text = listOfHeadlines[position].author
            readLaterText.setOnClickListener { onClickListener?.saveLater(listOfHeadlines[position]) }
            Glide.with(context)
                .load(listOfHeadlines[position].pic)
                .into(articleImage);

        }
    }

    interface OnClickListener {
        fun onClick(url: String)
        fun saveLater(news: NewsHeadlines)
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

    fun updateHeadlines(newHeadlines: List<NewsHeadlines>) {
        listOfHeadlines = emptyList()
        listOfHeadlines = newHeadlines
        notifyDataSetChanged()

    }
}


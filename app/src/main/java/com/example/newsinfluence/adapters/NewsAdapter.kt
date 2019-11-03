package com.example.newsinfluence.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsinfluence.R
import com.example.newsinfluence.models.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val items: ArrayList<News>):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView?.apply {
            text = items[position].headline
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView? = view.tv_title

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }
}
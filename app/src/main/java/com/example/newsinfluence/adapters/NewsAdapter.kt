package com.example.newsinfluence.adapters

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsinfluence.R
import com.example.newsinfluence.models.News
import kotlinx.android.synthetic.main.item_news.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = format.parse(items[position].time)
        val day = DateFormat.format("dd", date).toString()
        val month = DateFormat.format("MM", date).toString()
        val year = DateFormat.format("yyyy", date).toString()

        holder.dateTextView?.apply {
            text = " Date: " + day + "." + month + "." + year
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView? = view.tv_title
        val dateTextView: TextView? = view.tv_date

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }
}
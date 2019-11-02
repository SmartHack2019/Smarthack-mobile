package com.example.newsinfluence.adapters

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsinfluence.R
import com.example.newsinfluence.models.Company
import kotlinx.android.synthetic.main.item_company.view.*

class CompaniesAdapter(private val items: ArrayList<Company>):
    RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_company, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.codeTextView?.apply {
            text = items[position].code
        }

        holder.nameTextView?.apply {
            text = items[position].name
        }

        holder.changeTextView?.apply {
            text = items[position].change.toString()
        }

        if (items[position].change > 0.0f) {
            holder.changeTextView?.setTextColor(Color.GREEN)
        } else {
            holder.changeTextView?.setTextColor(Color.RED)
        }

        holder.priceTextView?.apply {
            text = items[position].price.toString() + " £"
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val codeTextView: TextView? = view.tv_code
        val nameTextView: TextView? = view.tv_name
        val changeTextView: TextView? = view.tv_change
        val priceTextView: TextView? = view.tv_price

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }
}
package com.example.newsinfluence.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsinfluence.R
import com.example.newsinfluence.adapters.NewsAdapter
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.models.Company
import com.example.newsinfluence.models.News
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_companies.*
import kotlinx.android.synthetic.main.fragment_company_details.*

class CompanyDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(): CompanyDetailsFragment {
            return CompanyDetailsFragment()
        }
    }

    private lateinit var mCompany: Company
    private var mNewsList = ArrayList<News>()
    private lateinit var mNewsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        createFakeNews()
        setupNewsList()

        val entries = ArrayList<Entry>()

        for(i in 1..10) {
            entries.add(Entry(i.toFloat(), i.toFloat()))
        }

        val dataSet = LineDataSet(entries, "label")
        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()
    }

    fun setupUI() {
        mCompany = arguments?.getParcelable(Constants.Keys.COMPANY) ?: return

        setActionBarTitle(mCompany.name)

        tv_price_value.text = mCompany.price.toString() + " £"
        tv_company_name_news.text = mCompany.name + " News"
        tv_change_value.text = mCompany.change.toString()

        if (mCompany.change > 0.0f) {
            tv_change_value.setTextColor(Color.GREEN)
        } else {
            tv_change_value.setTextColor(Color.RED)
        }
    }

    private fun setupNewsList() {
        mNewsAdapter = NewsAdapter(mNewsList)

        val linearLayoutManager = LinearLayoutManager(context)

        rv_news.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = mNewsAdapter
        }

        mNewsAdapter.onItemClick = { selectItem(it) }
    }

    private fun selectItem(position: Int) {
        val selectedItem = mNewsList[position]
    }

    private fun createFakeNews() {
        val news = arrayListOf<News>()
        news.add(News("Google Fi is offering the Pixel 3A for just $299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just \$299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just \$299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just \$299"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just \$299"))
        mNewsList = news
    }
}

package com.example.newsinfluence.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsinfluence.R
import com.example.newsinfluence.adapters.NewsAdapter
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.models.Company
import com.example.newsinfluence.models.News
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
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
        setupChart()
    }

    fun setupChart() {
        val ctx = context ?: return
        val entries = ArrayList<Entry>()

        for(i in 1..10) {
            if (i % 2 == 0) {
                entries.add(Entry(i.toFloat(), i.toFloat() - 2))
            } else {
                entries.add(Entry(i.toFloat(), i.toFloat() + 2))
            }
        }

        val dataSet = LineDataSet(entries, "label")
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER)
        dataSet.setDrawFilled(true)
        dataSet.setDrawValues(false)
        dataSet.setFillColor(ContextCompat.getColor(ctx, R.color.pale_green))
        dataSet.setColor(ContextCompat.getColor(ctx, R.color.pale_green))
        dataSet.setDrawCircles(false)
        dataSet.setDrawHorizontalHighlightIndicator(false)
        dataSet.setDrawVerticalHighlightIndicator(false)
        chart.getXAxis().setDrawGridLines(false)
        chart.getDescription().setText("")
        chart.getLegend().setEnabled(false)
        chart.getAxisRight().setEnabled(false)

        chart.getAxisLeft().setValueFormatter(object : IAxisValueFormatter{
            override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                return String.format("%.2f %",value)
            }
        })

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

        val bundle = Bundle()
        bundle.putParcelable(
            Constants.Keys.NEWS,
            selectedItem
        )
        onReplaceFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_NEWS_DETAILS, bundle)
    }

    private fun createFakeNews() {
        val news = arrayListOf<News>()
        news.add(News("Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        news.add(News("Google Fi is offering the Pixel 3A for just $299 Google Fi is offering the Pixel 3A for just $299",
            "https://www.google.com/"))
        mNewsList = news
    }
}

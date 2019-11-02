package com.example.newsinfluence.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.newsinfluence.R
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.models.Company
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_company_details.*

class CompanyDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(): CompanyDetailsFragment {
            return CompanyDetailsFragment()
        }
    }

    private lateinit var mCompany: Company

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

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

        tv_price_value.text = mCompany.price.toString() + " £"
        tv_company_name_news.text = mCompany.name + " News"
        tv_change_value.text = mCompany.change.toString()

        if (mCompany.change > 0.0f) {
            tv_change_value.setTextColor(Color.GREEN)
        } else {
            tv_change_value.setTextColor(Color.RED)
        }
    }
}

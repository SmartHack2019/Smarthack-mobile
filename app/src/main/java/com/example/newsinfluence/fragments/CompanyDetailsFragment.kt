package com.example.newsinfluence.fragments

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

        mCompany = arguments?.getParcelable(Constants.Keys.COMPANY) ?: return

        val entries = ArrayList<Entry>()

        for(i in 1..10) {
            entries.add(Entry(i.toFloat(), i.toFloat()))
        }

        val dataSet = LineDataSet(entries, "label")
        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()
    }
}

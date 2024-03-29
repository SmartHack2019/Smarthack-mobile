package com.example.newsinfluence.fragments

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsinfluence.R
import com.example.newsinfluence.adapters.CompaniesAdapter
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.interfaces.OnRequestDoneWithResult
import com.example.newsinfluence.models.Company
import com.example.newsinfluence.models.GetCompaniesResponse
import com.example.newsinfluence.requests.GetCompaniesRequest
import kotlinx.android.synthetic.main.fragment_companies.*

class CompaniesFragment : BaseFragment() {

    companion object {
        fun newInstance(): CompaniesFragment {
            return CompaniesFragment()
        }
    }

    private var mCompaniesList = ArrayList<Company>()
    private lateinit var mCompaniesAdapter: CompaniesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle("FTSE 100 Index")
        setupCompaniesList()
        getCompanies()
    }

    private fun setupCompaniesList() {
        mCompaniesAdapter = CompaniesAdapter(mCompaniesList)

        val linearLayoutManager = LinearLayoutManager(context)

        rv_companies.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = mCompaniesAdapter
        }

        mCompaniesAdapter.onItemClick = { selectItem(it) }
    }

    private fun selectItem(position: Int) {
        val selectedItem = mCompaniesList[position]

        val bundle = Bundle()
        bundle.putParcelable(
            Constants.Keys.COMPANY,
            selectedItem
        )
        onReplaceFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_COMPANY_DETAILS, bundle)
    }

    private fun getCompanies() {
        val ctx = context ?: return
        mAlertCallback?.showProgressDialog()
        GetCompaniesRequest(getCompaniesListener, ctx).execute()
    }

    private val getCompaniesListener = object : OnRequestDoneWithResult {
        override fun onRequestSuccess(result: Any) {
            val companies = result as? ArrayList<Company> ?: return

            mCompaniesList.clear()
            mCompaniesList.addAll(companies)
            mCompaniesAdapter.notifyDataSetChanged()
            mAlertCallback?.hideProgressDialog()
        }

        override fun onRequestFailed(errorMessage: String, unauthorized: Boolean) {
            mAlertCallback?.showAlert(errorMessage)
        }
    }
}

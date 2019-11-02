package com.example.newsinfluence.activities

import android.os.Bundle
import com.example.newsinfluence.R
import com.example.newsinfluence.fragments.BaseFragment
import com.example.newsinfluence.fragments.CompanyDetailsFragment
import com.example.newsinfluence.fragments.CompaniesFragment
import com.example.newsinfluence.fragments.NewsDetailsFragment
import com.example.newsinfluence.helpers.Constants

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        onAddFragment(Constants.FragmentTags.TAG_FRAGMENT_COMPANIES)
    }

    override fun getFragmentContainer(): Int? {
        return R.id.fragments_container_home
    }

    override fun getFragmentByTag(TAG: String): BaseFragment? = when (TAG) {
        Constants.FragmentTags.TAG_POP ->
            mFragmentManager?.findFragmentByTag(TAG) as BaseFragment

        Constants.FragmentTags.TAG_FRAGMENT_COMPANIES ->
            CompaniesFragment.newInstance()

        Constants.FragmentTags.TAG_FRAGMENT_COMPANY_DETAILS ->
            CompanyDetailsFragment.newInstance()

        Constants.FragmentTags.TAG_FRAGMENT_NEWS_DETAILS ->
            NewsDetailsFragment.newInstance()

        else -> null
    }
}

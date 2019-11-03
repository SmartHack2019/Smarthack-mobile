package com.example.newsinfluence.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsinfluence.R
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.models.News
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(): NewsDetailsFragment {
            return NewsDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news: News = arguments?.getParcelable(Constants.Keys.NEWS) ?: return
        setActionBarTitle(news.headline)
        webview.loadUrl(news.link)
        webview.settings.javaScriptEnabled = true
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
    }
}

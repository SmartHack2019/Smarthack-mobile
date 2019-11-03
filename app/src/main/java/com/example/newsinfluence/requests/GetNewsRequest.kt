package com.example.newsinfluence.requests

import android.content.Context
import android.util.Log
import com.example.newsinfluence.helpers.api.UtilsAPI
import com.example.newsinfluence.interfaces.OnRequestDoneWithResult
import com.example.newsinfluence.models.GetNewsResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GetNewsRequest(
    mListener: OnRequestDoneWithResult,
    context: Context
) : BaseRequest(mListener, context) {

    private val TAG = "GetNewsRequest"

    fun execute(companyId: String) {
        val mApiService = UtilsAPI.apiService ?: return

        mApiService
            .getNews(companyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<GetNewsResponse>() {
                override fun onCompleted() {
                    Log.i(TAG, "onCompleted")
                }

                override fun onError(throwable: Throwable) {
                    onParseErrorResult(throwable)
                }

                override fun onNext(response: GetNewsResponse) {
                    Log.i(TAG, "onNext")
                    mListener.onRequestSuccess(response.news)
                }
            })
    }
}
package com.example.newsinfluence.requests

import android.content.Context
import android.util.Log
import com.example.newsinfluence.helpers.api.UtilsAPI
import com.example.newsinfluence.interfaces.OnRequestDoneWithResult
import com.example.newsinfluence.models.GetCompaniesResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GetCompaniesRequest(
    mListener: OnRequestDoneWithResult,
    context: Context
) : BaseRequest(mListener, context) {

    private val TAG = "GetCompaniesRequest"

    fun execute() {
        val mApiService = UtilsAPI.apiService ?: return

        mApiService
            .getCompanies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<GetCompaniesResponse>() {
                override fun onCompleted() {
                    Log.i(TAG, "onCompleted")
                }

                override fun onError(throwable: Throwable) {
                    onParseErrorResult(throwable)
                }

                override fun onNext(response: GetCompaniesResponse) {
                    Log.i(TAG, "onNext")
                    mListener.onRequestSuccess(response.companies)
                }
            })
    }
}
package com.example.newsinfluence.requests

import android.content.Context
import com.example.newsinfluence.R
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.interfaces.OnRequestDoneWithResult
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

open class BaseRequest(
    protected val mListener: OnRequestDoneWithResult,
    protected val context: Context
) {

    protected fun onParseErrorResult() {
        val errorMessage = context.getString(R.string.alert_error_null)
        mListener.onRequestFailed(errorMessage)
    }

    protected fun onParseErrorResult(throwable: Throwable?) {
        onParseErrorResult(throwable, null)
    }

    protected fun onParseErrorResult(throwable: Throwable?, checkStatus: ArrayList<Pair<Int, String>>?) {
        var errorMessage = context.getString(R.string.alert_error_null)

        if (throwable is HttpException) {
            val errorStatus = throwable.response().code()

            checkStatus?.forEach {
                if (it.first == errorStatus) {
                    mListener.onRequestFailed(it.second)
                    return
                }
            }

            if(errorStatus == Constants.ApiStatus.REQUEST_CODE_401) {
                errorMessage = context.getString(R.string.you_have_been_logged_out)
                mListener.onRequestFailed(errorMessage, true)
                return
            }

            try {
                val errorBody = throwable.response().errorBody()?.string() ?: run {
                    mListener.onRequestFailed(errorMessage)
                    return
                }

                if (errorBody.isEmpty()) {
                    mListener.onRequestFailed(errorMessage)
                    return
                }

                val errorJSON = JSONObject(errorBody)
                val message = errorJSON.getString(Constants.Keys.MESSAGE)
                mListener.onRequestFailed(message)
                return
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        mListener.onRequestFailed(errorMessage)
    }
}
package com.example.newsinfluence.interfaces

import android.support.v7.app.AlertDialog

interface OnActivityAlert {
    fun showProgressDialog()
    fun hideProgressDialog()

    fun showAlert(message: String): AlertDialog
    fun showAlert(
        message: String,
        negativeButtonText: String,
        mRequestAction: OnRequestAction?,
        isCancelable: Boolean = false
    ): AlertDialog

    fun showAlert(
        message: String, positiveButtonText: String, negativeButtonText: String,
        mRequestAction: OnRequestAction?, isCancelable: Boolean = false
    ): AlertDialog

    fun showToast(message: String)
}
package com.example.newsinfluence.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.newsinfluence.interfaces.OnActivityAction
import com.example.newsinfluence.interfaces.OnActivityAlert
import com.example.newsinfluence.interfaces.OnActivityFragmentCommunication

open class BaseFragment : Fragment() {

    var mAlertCallback: OnActivityAlert? = null
    var mHandleFragmentsCallback: OnActivityFragmentCommunication? = null
    var mActivityAction: OnActivityAction? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnActivityFragmentCommunication) {
            mHandleFragmentsCallback = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

        if (context is OnActivityAction) {
            mActivityAction = context
        }

        if (context is OnActivityAlert) {
            mAlertCallback = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mHandleFragmentsCallback = null
        mAlertCallback = null
    }

    /**
     * Handle fragments by tag
     */
    protected fun onAddFragmentByTAG(TAG: String, bundle: Bundle? = null) =
        mHandleFragmentsCallback?.onAddFragment(TAG, bundle)

    protected fun onReplaceFragmentByTAG(TAG: String, bundle: Bundle? = null) =
        mHandleFragmentsCallback?.onReplaceFragment(TAG, bundle)

    protected fun onRemoveFragmentByTAG(TAG: String) =
        mHandleFragmentsCallback?.onRemoveFragment(TAG)

    protected fun setActionBarTitle(title: String) =
        mHandleFragmentsCallback?.setActionBarTitle(title)

    /**
     * Show alerts
     */
    protected fun showProgressDialog() =
        mAlertCallback?.showProgressDialog()

    protected fun hideProgressDialog() =
        mAlertCallback?.hideProgressDialog()


    /**
     * Hide keyboard
     */
    fun hideKeyboardFrom(view: View) {
        val ctx = context ?: return

        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
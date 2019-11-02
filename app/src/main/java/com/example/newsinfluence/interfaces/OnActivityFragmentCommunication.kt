package com.example.newsinfluence.interfaces

import android.os.Bundle

interface OnActivityFragmentCommunication {
    fun onAddFragment(TAG: String, bundle: Bundle? = null)
    fun onReplaceFragment(TAG: String, bundle: Bundle? = null)
    fun onRemoveFragment(TAG: String)
    fun setActionBarTitle(title: String)
}
package com.example.newsinfluence.interfaces

interface OnActivityAction {
    fun setToolbarTitle(title: String)
    fun goToWelcomePage()
    fun logoutUser(isForced: Boolean = false)
    fun forcedLogOut()
}
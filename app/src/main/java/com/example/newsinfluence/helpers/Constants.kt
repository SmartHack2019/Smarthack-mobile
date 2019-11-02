package com.example.newsinfluence.helpers

import java.util.regex.Pattern

class Constants {

    companion object {
        const val CAMERA_REQUEST_CODE = 101
        const val CAMERA_PERMISSION_DENIED = 102
        const val NEVER_ASK_ABOUT_CAMERA_PERMISSION = 103
    }

    object ValidationRules {
        const val PASSWORD_MIN_CHARACTERS = 6
    }

    object Keys {
        const val MESSAGE = "message"
        const val STATUS = "status"
        const val REFRESH_TOKEN = "refresh_token"
        const val KEY = "key"
        const val COMPANY = "company"
        const val NEWS = "news"
    }

    object ApiStatus {
        const val ERROR_REQUEST = "ERROR"
        const val ERROR_NULL = "error_null"
        const val REQUEST_CODE_401 = 401
        const val REQUEST_CODE_403 = 403
        const val REQUEST_CODE_405 = 405
        const val REQUEST_CODE_400 = 400
        const val REQUEST_CODE_500 = 500
        const val REQUEST_CODE_200 = 200
        const val REQUEST_CODE_ERROR_NULL = 1

        const val ERROR_USER_NOT_FOUND = "error_no_user_found"
    }

    object FragmentTags {
        const val TAG_POP = "pop_fragment"
        const val TAG_FRAGMENT_COMPANIES = "TAG_FRAGMENT_COMPANIES"
        const val TAG_FRAGMENT_COMPANY_DETAILS = "TAG_FRAGMENT_COMPANY_DETAILS"
        const val TAG_FRAGMENT_NEWS_DETAILS = "TAG_FRAGMENT_NEWS_DETAILS"
    }

    object Patterns {
        val NAME_PATTERN = Pattern.compile("[a-zA-z]+([ \'-][a-zA-Z]+)*")
    }
}
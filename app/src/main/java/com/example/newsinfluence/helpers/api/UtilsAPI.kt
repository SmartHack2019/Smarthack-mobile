package com.example.newsinfluence.helpers.api

import com.example.newsinfluence.BuildConfig

object UtilsAPI {

    var BASE_API: String = ""
    var DEV_API: String = "http://test-app-service-m.azurewebsites.net"

    val BASE_URL = ""
    val DEV_URL = "http://test-app-service-m.azurewebsites.net" //local

    val apiService: APIService?
        get() {
            try {
                val url = getBaseApi()

                return RetrofitClient.getClient(url).create(APIService::class.java)
            } catch (ex: IllegalArgumentException) {
                ex.printStackTrace()
            }

            return null
        }

    fun getBaseApi(): String {
        return if(BuildConfig.DEBUG) {
            DEV_API
        } else {
            BASE_API
        }
    }

    fun getBaseURL(): String {
        return if(BuildConfig.DEBUG) {
            DEV_URL
        } else {
            BASE_URL
        }
    }
}
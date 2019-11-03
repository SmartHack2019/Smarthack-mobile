package com.example.newsinfluence.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Point(@SerializedName("date")
                 @Expose val date: Int,
                 @SerializedName("change")
                 @Expose val change: Float)
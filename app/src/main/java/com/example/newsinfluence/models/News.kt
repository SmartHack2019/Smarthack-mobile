package com.example.newsinfluence.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(@SerializedName("title")
                @Expose val title: String,
                @SerializedName("url")
                @Expose val url: String): Parcelable
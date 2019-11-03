package com.example.newsinfluence.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(@SerializedName("id")
                @Expose val id: String,
                @SerializedName("headline")
                @Expose val headline: String,
                @SerializedName("link")
                @Expose val link: String,
                @SerializedName("time")
                @Expose val time: String,
                @SerializedName("impact")
                @Expose val impact: Float): Parcelable
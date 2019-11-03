package com.example.newsinfluence.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(@SerializedName("code")
                   @Expose val code: String,
                   @SerializedName("name")
                   @Expose val name: String,
                   @SerializedName("price")
                   @Expose val price: Float,
                   @SerializedName("change")
                   @Expose val change: Float): Parcelable
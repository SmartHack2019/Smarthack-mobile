package com.example.newsinfluence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(val code: String,
                   val name: String,
                   val price: Float,
                   val change: Float): Parcelable
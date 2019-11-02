package com.example.newsinfluence.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Session(
    @SerializedName("access_token")
    @Expose val token: String,
    @SerializedName("refresh_token")
    @Expose val refreshToken: String
)
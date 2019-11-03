package com.example.newsinfluence.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetCompaniesResponse(
    @SerializedName("companies")
    @Expose val companies: ArrayList<Company>
)
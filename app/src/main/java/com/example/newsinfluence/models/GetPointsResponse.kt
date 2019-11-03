package com.example.newsinfluence.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetPointsResponse(@SerializedName("points")
                             @Expose val points: ArrayList<Point>)
package com.example.newsinfluence.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetNewsResponse(@SerializedName("items")
                      @Expose val news: ArrayList<News>)
package com.example.newsinfluence.helpers.api

import com.example.newsinfluence.models.GetCompaniesResponse
import retrofit2.http.GET
import rx.Observable

interface APIService {
    @GET("/api/companies")
    fun getCompanies(): Observable<GetCompaniesResponse>
}
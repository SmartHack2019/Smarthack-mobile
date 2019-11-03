package com.example.newsinfluence.helpers.api

import com.example.newsinfluence.models.GetCompaniesResponse
import com.example.newsinfluence.models.GetNewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface APIService {
    @GET("/api/companies")
    fun getCompanies(): Observable<GetCompaniesResponse>

    @GET("/api/news/company/{companyId}")
    fun getNews(
        @Path("companyId") companyId: String
    ): Observable<GetNewsResponse>
}
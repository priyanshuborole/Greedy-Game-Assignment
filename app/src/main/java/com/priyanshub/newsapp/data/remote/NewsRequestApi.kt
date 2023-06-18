package com.priyanshub.newsapp.data.remote

import com.priyanshub.newsapp.commons.Constants.Companion.API_KEY
import com.priyanshub.newsapp.domain.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRequestApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String = "us",
        @Query("page")
        pageNumber:Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber:Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY
    ): Response<NewsResponse>
}
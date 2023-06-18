package com.priyanshub.newsapp.domain.repository

import com.priyanshub.newsapp.domain.models.NewsResponse
import retrofit2.Response

interface NewsApiRepository {
    suspend fun getBreakingNews(countryCode : String, pageNumber : Int) : Response<NewsResponse>
    suspend fun searchNews(searchQuery: String, pageNumber: Int) : Response<NewsResponse>
}
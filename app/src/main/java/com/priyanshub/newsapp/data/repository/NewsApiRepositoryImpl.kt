package com.priyanshub.newsapp.data.repository

import com.priyanshub.newsapp.data.remote.NewsRequestApi
import com.priyanshub.newsapp.domain.models.NewsResponse
import com.priyanshub.newsapp.domain.repository.NewsApiRepository
import retrofit2.Response

class NewsApiRepositoryImpl(
    private val api: NewsRequestApi
): NewsApiRepository {
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) : Response<NewsResponse> {
        return api.getBreakingNews(countryCode,pageNumber)
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) : Response<NewsResponse>{
        return api.searchNews(searchQuery, pageNumber)
    }
}
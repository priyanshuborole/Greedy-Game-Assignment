package com.priyanshub.newsapp.domain.usecase

import com.priyanshub.newsapp.commons.Resource
import com.priyanshub.newsapp.domain.models.NewsResponse
import com.priyanshub.newsapp.domain.repository.NewsApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val repository: NewsApiRepository
) {
    operator fun invoke(
        countryCode : String, pageNumber : Int
    ): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getBreakingNews(countryCode, pageNumber)
            emit(Resource.Success(response.body()!!))
        } catch (e: Exception)
        {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}
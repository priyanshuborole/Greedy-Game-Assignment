package com.priyanshub.newsapp.domain.usecase

import androidx.lifecycle.LiveData
import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.domain.repository.NewsDBRepository
import javax.inject.Inject

class GetSavedArticleUseCase @Inject constructor(
    private val repository: NewsDBRepository
) {
     operator fun invoke(): LiveData<List<Article>>?{
        return try {
            repository.getSavedNews()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
package com.priyanshub.newsapp.domain.usecase

import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.domain.repository.NewsDBRepository
import javax.inject.Inject

class InsertArticleUseCase @Inject constructor(
    private val repository: NewsDBRepository
) {
    suspend operator fun invoke(article: Article){
        try {
            repository.insert(article)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
package com.priyanshub.newsapp.domain.usecase

import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.domain.repository.NewsDBRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: NewsDBRepository
) {
    suspend operator fun invoke(article: Article){
        try {
            repository.delete(article)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
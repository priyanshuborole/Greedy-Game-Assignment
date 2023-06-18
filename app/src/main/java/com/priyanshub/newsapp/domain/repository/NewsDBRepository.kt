package com.priyanshub.newsapp.domain.repository

import androidx.lifecycle.LiveData
import com.priyanshub.newsapp.domain.models.Article

interface NewsDBRepository {
    suspend fun insert(article : Article)

    fun getSavedNews(): LiveData<List<Article>>

    suspend fun delete(article: Article)
}
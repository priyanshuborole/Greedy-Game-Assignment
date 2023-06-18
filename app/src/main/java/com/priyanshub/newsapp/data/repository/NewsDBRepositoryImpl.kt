package com.priyanshub.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.priyanshub.newsapp.data.local.ArticleDao
import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.domain.repository.NewsDBRepository

class NewsDBRepositoryImpl(
    private val articleDao: ArticleDao
): NewsDBRepository {
    override suspend fun insert(article: Article) {
        articleDao.insertArticle(article)
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun delete(article: Article) {
        articleDao.deleteArticle(article)
    }
}
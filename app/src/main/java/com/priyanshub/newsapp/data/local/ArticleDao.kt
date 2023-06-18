package com.priyanshub.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.priyanshub.newsapp.domain.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}
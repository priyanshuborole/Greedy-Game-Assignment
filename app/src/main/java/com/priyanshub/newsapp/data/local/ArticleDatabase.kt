package com.priyanshub.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.priyanshub.newsapp.domain.models.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DatabaseConvertor::class)
abstract class ArticleDatabase: RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao
}

package com.priyanshub.newsapp.di

import android.content.Context
import androidx.room.Room
import com.priyanshub.newsapp.commons.Constants
import com.priyanshub.newsapp.commons.Constants.Companion.ARTICLE_DATABASE
import com.priyanshub.newsapp.commons.interceptors.CacheInterceptor
import com.priyanshub.newsapp.commons.interceptors.ForceCacheInterceptor
import com.priyanshub.newsapp.data.local.ArticleDao
import com.priyanshub.newsapp.data.local.ArticleDatabase
import com.priyanshub.newsapp.data.remote.NewsRequestApi
import com.priyanshub.newsapp.data.repository.NewsApiRepositoryImpl
import com.priyanshub.newsapp.data.repository.NewsDBRepositoryImpl
import com.priyanshub.newsapp.domain.repository.NewsApiRepository
import com.priyanshub.newsapp.domain.repository.NewsDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    //Retrofit Api Module
    @Provides
    @Singleton
    fun provideNewsRequestApi(
        @ApplicationContext context: Context
    ): NewsRequestApi {
        val client = OkHttpClient().newBuilder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L))
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(context))
            .build();

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(NewsRequestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsApiRepository(api: NewsRequestApi): NewsApiRepository {
        return NewsApiRepositoryImpl(api)
    }

    //Room Db Module
    @Provides
    @Singleton
    fun provideArticleDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            ARTICLE_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDAO(articleDatabase: ArticleDatabase) = articleDatabase.getArticleDao()

    @Provides
    @Singleton
    fun provideNewsDBRepository(dao: ArticleDao): NewsDBRepository {
        return NewsDBRepositoryImpl(dao)
    }

}
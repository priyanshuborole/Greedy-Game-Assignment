package com.priyanshub.newsapp.presentation.breakingNews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshub.newsapp.commons.Resource
import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.domain.models.NewsResponse
import com.priyanshub.newsapp.domain.usecase.GetBreakingNewsUseCase
import com.priyanshub.newsapp.domain.usecase.InsertArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val breakingNewsUseCase: GetBreakingNewsUseCase,
    private val insertArticleUseCase: InsertArticleUseCase
): ViewModel() {

    val breakingNews : MutableLiveData<NewsResponse> = MutableLiveData()
    val showProgress : MutableLiveData<Boolean> = MutableLiveData()

    init {
        getBreakingNews("in",1)
    }

    private fun getBreakingNews(countryCode: String, pageNumber: Int) = viewModelScope.launch{
        breakingNewsUseCase.invoke(countryCode, pageNumber).collect{
            //breakingNews.postValue(it)
            when(it){
                is Resource.Loading -> {
                    showProgress.postValue(true)
                }
                is Resource.Error -> {
                    showProgress.postValue(false)
                }
                is Resource.Success -> {
                    breakingNews.postValue(it.data!!)
                    showProgress.postValue(false)
                }
                else -> {}
            }
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        insertArticleUseCase(article = article)
    }

}
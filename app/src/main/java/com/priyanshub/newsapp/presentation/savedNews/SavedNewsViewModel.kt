package com.priyanshub.newsapp.presentation.savedNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshub.newsapp.domain.models.Article
import com.priyanshub.newsapp.domain.models.NewsResponse
import com.priyanshub.newsapp.domain.usecase.DeleteArticleUseCase
import com.priyanshub.newsapp.domain.usecase.GetSavedArticleUseCase
import com.priyanshub.newsapp.domain.usecase.InsertArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
): ViewModel() {
    val savedNews : MutableLiveData<NewsResponse> = MutableLiveData()
    val showProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun getSavedNews(): LiveData<List<Article>>? {
        return getSavedArticleUseCase()
    }

    fun deleteArticle(article: Article)=viewModelScope.launch(Dispatchers.IO){
        deleteArticleUseCase(article)
    }
}
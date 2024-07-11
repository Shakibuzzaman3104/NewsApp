package com.crustpizza.newstest.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelTopNewsSources
import com.crustpizza.newstest.domain.NewsSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val newsSourceUseCase: NewsSourceUseCase) :
    ViewModel() {

    var news = MutableStateFlow<Result<ModelTopNewsSources>?>(null)

    fun getData() {
        viewModelScope.launch {
            news.emit(newsSourceUseCase.invoke().first())
        }
    }


}
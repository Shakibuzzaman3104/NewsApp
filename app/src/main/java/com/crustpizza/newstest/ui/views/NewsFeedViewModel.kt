package com.crustpizza.newstest.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crustpizza.newstest.data.model.ModelNewsResponse
import com.crustpizza.newstest.data.repository.HeadlinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val headlinesRepo: HeadlinesRepository) :
    ViewModel() {

    private var _headlines = MutableSharedFlow<ModelNewsResponse>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val headlines = _headlines.flatMapLatest {
        headlinesRepo.fetchTopHeadlines()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    init {
        headlines
    }


}
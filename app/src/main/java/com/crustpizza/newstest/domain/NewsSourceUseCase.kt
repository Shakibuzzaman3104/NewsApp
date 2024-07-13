package com.crustpizza.newstest.domain

import android.util.Log
import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelTopNewsSources
import com.crustpizza.newstest.domain.irepository.INewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsSourceUseCase @Inject constructor(private val repository: INewsSourcesRepository) {

    suspend operator fun invoke(): Flow<Result<ModelTopNewsSources>> {

       return  repository.fetchNewsSources()
    }

}
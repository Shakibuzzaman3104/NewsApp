package com.crustpizza.newstest.domain.irepository

import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelTopNewsSources
import kotlinx.coroutines.flow.Flow

interface INewsSourcesRepository {
    suspend fun fetchNewsSources(): Flow<Result<ModelTopNewsSources>>

}
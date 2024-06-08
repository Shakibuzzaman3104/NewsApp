package com.crustpizza.newstest.domain.irepository

import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelNewsResponse
import kotlinx.coroutines.flow.Flow

interface IHeadlinesRepository {

    suspend fun fetchTopHeadlines(country: String?, category:String?): Flow<Result<ModelNewsResponse>>

}
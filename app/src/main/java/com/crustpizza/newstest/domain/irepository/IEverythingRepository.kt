package com.crustpizza.newstest.domain.irepository

import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelNewsResponse
import kotlinx.coroutines.flow.Flow

interface IEverythingRepository {

    suspend fun fetchEverything(): Flow<Result<ModelNewsResponse>>

}
package com.crustpizza.newstest.data.repository

import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelNewsResponse
import com.crustpizza.newstest.data.remote.NetworkResource
import com.crustpizza.newstest.data.remote.api.HeadlinesApiInterface
import com.crustpizza.newstest.di.IoDispatcher
import com.crustpizza.newstest.domain.irepository.IHeadlinesRepository
import com.crustpizza.newstest.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class HeadlinesRepository(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val apiInterface: HeadlinesApiInterface
) : IHeadlinesRepository {

    private val controlledRunner = ControlledRunner<Flow<Result<ModelNewsResponse>>>()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchTopHeadlines(
        country: String?,
        category: String?
    ): Flow<Result<ModelNewsResponse>> {
        return controlledRunner.cancelPreviousThenRun {
            object : NetworkResource<ModelNewsResponse>(dispatcher) {
                override suspend fun createCall() = apiInterface.fetchHeadlines()
            }.asFlow()
        }
    }
}
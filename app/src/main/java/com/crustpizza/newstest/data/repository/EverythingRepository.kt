package com.crustpizza.newstest.data.repository

import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelNewsResponse
import com.crustpizza.newstest.data.remote.NetworkResource
import com.crustpizza.newstest.data.remote.api.EverythingApiInterface
import com.crustpizza.newstest.di.IoDispatcher
import com.crustpizza.newstest.domain.irepository.IEverythingRepository
import com.crustpizza.newstest.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EverythingRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: EverythingApiInterface
) : IEverythingRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchEverything(): Flow<Result<ModelNewsResponse>> {
        val controlledRunner: ControlledRunner<Flow<Result<ModelNewsResponse>>> = ControlledRunner()
        return controlledRunner.cancelPreviousThenRun {
            object : NetworkResource<ModelNewsResponse>(dispatcher) {
                override suspend fun createCall() = api.fetchEveryNews()
            }.asFlow()
        }
    }
}
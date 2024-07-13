package com.crustpizza.newstest.data.repository

import android.util.Log
import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelTopNewsSources
import com.crustpizza.newstest.data.remote.ApiResponse
import com.crustpizza.newstest.data.remote.NetworkResource
import com.crustpizza.newstest.data.remote.api.TopNewsSourcesApi
import com.crustpizza.newstest.di.IoDispatcher
import com.crustpizza.newstest.domain.irepository.INewsSourcesRepository
import com.crustpizza.newstest.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsSourcesRepository @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    val api: TopNewsSourcesApi
) :
    INewsSourcesRepository {

    private val controlledRunner: ControlledRunner<Flow<Result<ModelTopNewsSources>>> =
        ControlledRunner()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchNewsSources(): Flow<Result<ModelTopNewsSources>> {

        return controlledRunner.cancelPreviousThenRun {
            object : NetworkResource<ModelTopNewsSources>(dispatcher) {
                override suspend fun createCall(): Flow<ApiResponse<ModelTopNewsSources>>{
                    Log.d("Kita", "invoke: ")
                    return  api.fetchTopNewsSources()
                }
            }.asFlow()
        }

    }
}
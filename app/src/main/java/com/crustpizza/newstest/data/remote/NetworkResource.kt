package com.crustpizza.newstest.data.remote

import androidx.annotation.MainThread
import com.crustpizza.newstest.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
abstract class NetworkResource<RequestType>(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) {
    suspend fun asFlow(): Flow<com.crustpizza.newstest.data.Result<RequestType>> = flow {
        emit(com.crustpizza.newstest.data.Result.loading(null))
        when (val response = createCall().first()) {
            is ApiSuccessResponse -> {
                val data = response.body
                emit(com.crustpizza.newstest.data.Result.success(data))
            }
            is ApiEmptyResponse -> emit(com.crustpizza.newstest.data.Result.success(null))
            is ApiErrorResponse -> emit(
                com.crustpizza.newstest.data.Result.error(
                    msg = response.errorMessage,
                    data = null,
                    errorData = response.errorData,
                    statusCode = response.statusCode
                )
            )
        }
    }.flowOn(dispatcher)

    @MainThread
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
}

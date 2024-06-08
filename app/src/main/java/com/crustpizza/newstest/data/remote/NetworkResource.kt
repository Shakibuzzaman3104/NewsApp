package com.crustpizza.newstest.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.crustpizza.newstest.di.IoDispatcher
import com.crustpizza.newstest.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
abstract class NetworkResource<RequestType>(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) {
    suspend fun asFlow(): Flow<com.crustpizza.newstest.data.Result<RequestType>> {
        return createCall().transformLatest {
            when (it) {
                is ApiSuccessResponse -> {
                    val data = processResponse(it)
                    withContext(dispatcher) {
                        emit(com.crustpizza.newstest.data.Result.success(data))
                    }
                }

                is ApiEmptyResponse -> emit(com.crustpizza.newstest.data.Result.success(null))
                is ApiErrorResponse -> {
                    //onFetchFailed()
                    emit(
                        com.crustpizza.newstest.data.Result.error(
                            msg = it.errorMessage,
                            data = null,
                            errorData = it.errorData,
                            statusCode = it.statusCode
                        )
                    )
                }
            }
        }.onStart {
            emit(com.crustpizza.newstest.data.Result.loading(null))
        }
    }


    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
}

package com.crustpizza.newstest.data

import com.crustpizza.newstest.enums.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

data class Result<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val errorData: String? = null,
    val statusCode: Int? = null
) : Flow<Any> {
    companion object {
        fun <T> success(data: T?): Result<T> =
            Result(Status.SUCCESS, data, null)

        fun <T> error(
            msg: String,
            data: T? = null,
            errorData: String? = null,
            statusCode: Int? = null
        ): Result<T> =
            Result(Status.ERROR, data, msg, errorData, statusCode)

        fun <T> error(exception: Exception, data: T? = null): Result<T> =
            Result(Status.ERROR, data, exception.message)

        fun <T> loading(data: T? = null): Result<T> =
            Result(Status.LOADING, data, null)

        fun <T> nothing(): Result<T> =
            Result(Status.NOTHING, null, null)
    }

    override suspend fun collect(collector: FlowCollector<Any>) {
        TODO("Not yet implemented")
    }
}
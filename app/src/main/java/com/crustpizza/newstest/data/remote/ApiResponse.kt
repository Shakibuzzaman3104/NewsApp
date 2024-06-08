package com.crustpizza.newstest.data.remote

import com.crustpizza.newstest.data.model.ApiError
import com.crustpizza.newstest.utils.extensions.parseJson
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

sealed interface ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            Timber.d(error.toString())
            return when (error) {
                is IOException -> ApiErrorResponse("No internet connection", null, 0)
                else -> ApiErrorResponse(error.message ?: "Unknown error", null, 9)
            }
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return when {
                response.isSuccessful -> {
                    val body = response.body()
                    when {
                        body == null || response.code() == 204 -> ApiEmptyResponse()
                        else -> ApiSuccessResponse(body = body)
                    }
                }

                else -> {
                    Timber.d("${response.errorBody()}")
                    val msg = response.errorBody()?.string()
                    val errorMsg = if (msg.isNullOrEmpty()) {
                        response.message()
                    } else {
                        val apiError = msg.parseJson<ApiError>()
                        when {
                            apiError != null && apiError.message.isNotEmpty() -> apiError.message
                            else -> msg
                        }
                    }
                    ApiErrorResponse(errorMsg ?: "unknown error", msg, response.code())
                }
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>

data class ApiErrorResponse<T>(val errorMessage: String, val errorData: String? = null, val statusCode: Int? = null) :
    ApiResponse<T>
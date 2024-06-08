package com.crustpizza.newstest.data.remote.api

import com.crustpizza.newstest.BuildConfig
import com.crustpizza.newstest.data.Result
import com.crustpizza.newstest.data.model.ModelNewsResponse
import com.crustpizza.newstest.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingApiInterface {

    @GET("${BuildConfig.BASE_URL}everything")
    fun fetchEveryNews(
        @Query("q") query: String? = null,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Flow<ApiResponse<ModelNewsResponse>>

}
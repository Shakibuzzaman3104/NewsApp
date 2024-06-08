package com.crustpizza.newstest.data.remote.api

import com.crustpizza.newstest.BuildConfig
import com.crustpizza.newstest.data.model.ModelNewsResponse
import com.crustpizza.newstest.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query


interface HeadlinesApiInterface {

    @GET("${BuildConfig.BASE_URL}top-headlines")
    fun fetchHeadlines(
        @Query("country") query: String? = "us",
        @Query("category") sortBy: String = "technology",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Flow<ApiResponse<ModelNewsResponse>>

}
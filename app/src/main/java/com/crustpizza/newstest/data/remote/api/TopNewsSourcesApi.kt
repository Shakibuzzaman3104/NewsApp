package com.crustpizza.newstest.data.remote.api

import com.crustpizza.newstest.BuildConfig
import com.crustpizza.newstest.data.model.ModelTopNewsSources
import com.crustpizza.newstest.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface TopNewsSourcesApi {

    @GET("${BuildConfig.BASE_URL}top-headlines/sources")
    fun fetchTopNewsSources(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Flow<ApiResponse<ModelTopNewsSources>>
}
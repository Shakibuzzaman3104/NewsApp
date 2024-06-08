package com.crustpizza.newstest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModelNewsResponse(
    @Json(name = "articles")
    val articles: List<Article?>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "totalResults")
    val totalResults: Int?
)
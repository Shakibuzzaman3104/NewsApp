package com.crustpizza.newstest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModelTopNewsSources(
    @Json(name = "sources")
    val sources: List<TopNewsSource>?,
    @Json(name = "status")
    val status: String?
)
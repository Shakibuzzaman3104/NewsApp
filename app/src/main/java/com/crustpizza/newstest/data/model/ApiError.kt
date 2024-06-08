package com.crustpizza.newstest.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "message")
    val message: String
)

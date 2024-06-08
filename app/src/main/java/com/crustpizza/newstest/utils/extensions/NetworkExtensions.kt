package com.crustpizza.newstest.utils.extensions

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber


inline fun <reified T> String.parseJson(): T? {
    return try {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        jsonAdapter.fromJson(this)


    } catch (exception: Exception) {
        Timber.d(exception.message)
        null
    }
}
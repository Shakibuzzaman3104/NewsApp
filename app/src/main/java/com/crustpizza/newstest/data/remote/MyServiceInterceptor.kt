package com.crustpizza.newstest.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class MyServiceInterceptor @Inject constructor(
    @Named("version_name") private val versionName: String,
    @ApplicationContext private val appContext: Context
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //Timber.d("Network call intercepted ------------> ")
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        requestBuilder.method(request.method, request.body)
        return chain.proceed(requestBuilder.build())
    }
}
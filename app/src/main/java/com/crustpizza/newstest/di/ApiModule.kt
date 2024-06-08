package com.crustpizza.newstest.di

import com.crustpizza.newstest.data.remote.api.EverythingApiInterface
import com.crustpizza.newstest.data.remote.api.HeadlinesApiInterface
import com.crustpizza.newstest.data.remote.api.TopNewsSourcesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {

    @Provides
    fun provideEveryThingApi(retrofit: Retrofit): EverythingApiInterface {
        return retrofit.create(EverythingApiInterface::class.java)
    }

    @Provides
    fun provideHeadlinesApi(retrofit: Retrofit): HeadlinesApiInterface {
        return retrofit.create(HeadlinesApiInterface::class.java)
    }

    @Provides
    fun provideTopNewsSourceApi(retrofit: Retrofit): TopNewsSourcesApi {
        return retrofit.create(TopNewsSourcesApi::class.java)
    }

}
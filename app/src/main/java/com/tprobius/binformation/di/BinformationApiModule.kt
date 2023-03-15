package com.tprobius.binformation.di

import com.tprobius.binformation.data.api.ApiConstants
import com.tprobius.binformation.data.api.BinformationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BinformationApiModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): BinformationApi {
        return builder
            .build()
            .create(BinformationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}
package com.example.msmgrouptest.di

import com.example.msmgrouptest.data.data_sources.MsmApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "hostName"

    @Provides
    @Singleton
    @Named(BASE_URL)
    fun provideBaseUrlString():String = "https://fefufit.dvfu.ru"

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named(BASE_URL) baseUrl:String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MsmApi {
        return retrofit.create(MsmApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>):OkHttpClient{
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach{
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptors():ArrayList<Interceptor>{
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }

}
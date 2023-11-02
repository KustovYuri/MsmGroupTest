package com.example.msmgrouptest.di

import com.example.msmgrouptest.data.remote.data_sources.MsmApi
import com.example.msmgrouptest.data.remote.utils.BaseUrlManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "hostName"

    @Provides
    @Singleton
    @Named(BASE_URL)
    fun provideBaseUrlString():String = "https://test.wlbs.ru/"

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
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>,
        baseUrlManager: BaseUrlManager
    ):OkHttpClient{

        val trustAllCerts = arrayOf<TrustManager>(
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val clientBuilder = OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as  X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .followRedirects(false)
            .addInterceptor { chain ->
                val newUrl = chain.request().url.newBuilder()
                    .host(baseUrlManager.getHost())
                    .build()
                val newRequest = chain.request().newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }
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

    @Provides
    @Singleton
    fun providesBaseUrlManager(): BaseUrlManager {
        return BaseUrlManager()
    }

}
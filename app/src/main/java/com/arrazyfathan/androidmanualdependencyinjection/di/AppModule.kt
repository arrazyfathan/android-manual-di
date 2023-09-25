package com.arrazyfathan.androidmanualdependencyinjection.di

import android.content.Context
import com.arrazyfathan.androidmanualdependencyinjection.data.RepositoryImpl
import com.arrazyfathan.androidmanualdependencyinjection.data.ServiceApi
import com.arrazyfathan.androidmanualdependencyinjection.domain.Repository
import com.arrazyfathan.androidmanualdependencyinjection.utils.HttpLogger
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

interface AppModule {
    val interceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val gson: Gson
    val serviceApi: ServiceApi
    val repository: Repository
}

class AppModuleImpl(private val appContext: Context) : AppModule {
    override val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor(HttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    override val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    override val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    override val serviceApi: ServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.quotable.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create()
    }

    override val repository: Repository by lazy {
        RepositoryImpl(serviceApi)
    }


}
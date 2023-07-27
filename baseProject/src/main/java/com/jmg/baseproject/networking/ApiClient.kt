package com.jmg.baseproject.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiClient {

    const val DEBUG_URL = "https://sandbox.homeworkhelperapp.org"
    const val PROD_URL = "https://sandbox.homeworkhelperapp.org"
    val TAG = "ApiClient"

    val httpClient: OkHttpClient by lazy {

        OkHttpClient
            .Builder()
            .addNetworkInterceptor(Interceptor { chain ->
                val request = chain.request()
                val new = request.newBuilder()


                var response = chain.proceed(new.build())

                response
            })
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .callTimeout(60L, TimeUnit.SECONDS)
            .build()
    }
}
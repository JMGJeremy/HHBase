package com.jmg.baseproject.networking

import com.jmg.baseproject.models.auth.LoginApi
import com.jmg.baseproject.models.auth.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BaseService {

    @POST("users.json")
    suspend fun registerUser(
        @Body user: Any?
    ): Response<Any?>

    @POST("users/password.json")
    suspend fun forgotPassword(
        @Body email: Any?
    ): Response<Any?>

    @POST("oauth/token.json")
    suspend fun loginUser(
        @Body login: LoginApi
    ): Response<LoginResponse?>

    @POST("oauth/token.json")
    suspend fun refreshToken(
        @Body refresh: Any?
    ): Response<Any?>

    @GET("user/me.json")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): Response<Any?>
    companion object{
        private var apiService: BaseService? = null

        fun getInstance(baseUrl: String): BaseService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(ApiClient.httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(BaseService::class.java)
            }
            return apiService!!
        }
    }
}
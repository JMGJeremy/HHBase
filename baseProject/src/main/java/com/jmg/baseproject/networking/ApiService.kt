package com.jmg.baseproject.networking

import com.jmg.baseproject.models.auth.LoginResponse
import com.jmg.baseproject.models.auth.LoginApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("users.json")
    fun registerUser(
        @Body user: Any?
    ): Call<Any?>

    @POST("users/password.json")
    fun forgotPassword(
        @Body email: Any?
    ): Call<Any?>

    @POST("oauth/token.json")
    suspend fun loginUser(
        @Body login: LoginApi
    ): Response<LoginResponse?>

    @POST("oauth/token.json")
    fun refreshToken(
        @Body refresh: Any?
    ): Call<Any?>

    @GET("user/me.json")
    fun getUser(
        @Header("Authorization") token: String
    ):Call<Any?>
    companion object{
        private var apiService: ApiService? = null

        fun getInstance(baseUrl: String): ApiService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(ApiClient.httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
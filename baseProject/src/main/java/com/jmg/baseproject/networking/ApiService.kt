package com.jmg.baseproject.networking

import com.jmg.baseproject.models.auth.LoginApi
import retrofit2.Call
import retrofit2.Retrofit
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
    fun loginUser(
        @Body login: LoginApi
    ):Call<Any?>

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
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
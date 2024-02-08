package com.jmg.baseproject.networking

import com.jmg.baseproject.models.auth.*
import com.jmg.baseproject.models.device.AddDeviceRequest
import com.jmg.baseproject.models.device.RemoveDeviceRequest
import com.jmg.baseproject.models.device.RemoveDeviceResponse
import com.jmg.baseproject.models.device.UserDeviceResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

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
        @Body login: LoginRequest
    ): Response<Token?>

    @POST("oauth/token.json")
    suspend fun refreshToken(
        @Body refresh: Token?
    ): Response<Any?>

    @GET("user/me.json")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): Response<Any?>

    @GET("api_info.json")
    suspend fun getApiInfo(): Response<Any>

    @POST("/oauth/token.json")
    suspend fun refreshToken(
        @Body refresh: RefreshRequest
    ): Response<Token>

    @POST("users/{user_id}/devices/add.json")
    suspend fun addDevice(
        @Header("Authorization") token: String,
        @Path("user_id") userId: Int,
        @Body userDevice: AddDeviceRequest
    ): Response<UserDeviceResponse>

    @PATCH("users/{user_id}/devices/{device_id}.json")
    suspend fun updateDevice(
        @Header("Authorization") token: String,
        @Path("user_id") userId: Int,
        @Path("device_id") deviceId: Int
    ): Response<UserDeviceResponse>

    @POST("users/{user_id}/devices/remove.json")
    suspend fun removeDevice(
        @Header("Authorization") token: String,
        @Path("user_id") userId: Int,
        @Body removeDevice: RemoveDeviceRequest
    ): Response<RemoveDeviceResponse>

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
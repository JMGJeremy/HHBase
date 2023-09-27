package com.jmg.baseproject.models.auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "token")
data class LoginResponse(
    @PrimaryKey
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("created_at")
    val createdAt: Int?,
    @SerializedName("expires_in")
    val expiresIn: Int?,
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("token_type")
    val tokenType: String?
)
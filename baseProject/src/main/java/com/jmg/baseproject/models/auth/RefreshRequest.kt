package com.jmg.baseproject.models.auth

import com.google.gson.annotations.SerializedName

data class RefreshRequest(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("grant_type") val grantType: String
)
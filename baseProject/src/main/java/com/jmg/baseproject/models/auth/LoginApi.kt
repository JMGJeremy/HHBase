package com.jmg.baseproject.models.auth

import com.google.gson.annotations.SerializedName

data class LoginApi(
    val email: String,
    @SerializedName("grant_type")
    val grantType: String,
    val password: String
)
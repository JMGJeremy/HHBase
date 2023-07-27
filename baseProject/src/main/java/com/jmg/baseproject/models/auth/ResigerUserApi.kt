package com.jmg.baseproject.models.auth

import com.google.gson.annotations.SerializedName

data class UserRegisterApi(
    val email: String? = "",
    @SerializedName("first_name")
    val firstName: String? = "",
    @SerializedName("last_name")
    val lastName: String? = "",
    val password: String? = "",
    @SerializedName("password_confirmation")
    val confirmation: String? = "",
    @SerializedName("user_type")
    val type: String? = ""
)
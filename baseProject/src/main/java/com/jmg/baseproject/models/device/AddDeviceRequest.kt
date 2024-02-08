package com.jmg.baseproject.models.device

import com.google.gson.annotations.SerializedName

data class UserDevice(
    @SerializedName("firebase_key") val firebaseKey: String,
    @SerializedName("os") val os: String
)

data class UserDeviceRequest(
    @SerializedName("user_device") val userDevice: UserDevice
)

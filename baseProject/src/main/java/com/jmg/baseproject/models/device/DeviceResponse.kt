package com.jmg.baseproject.models.device

import com.google.gson.annotations.SerializedName

data class UserDeviceResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("firebase_key") val firebaseKey: String,
    @SerializedName("os") val os: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("microphone_active") val microphoneActive: Boolean,
    @SerializedName("camera_active") val cameraActive: Boolean,
    @SerializedName("notification_active") val notificationActive: Boolean
)
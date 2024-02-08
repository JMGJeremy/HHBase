package com.jmg.baseproject.models.device

import com.google.gson.annotations.SerializedName

data class UserDeviceUpdate(
    @SerializedName("microphone_active") val microphoneActive: Boolean,
    @SerializedName("camera_active") val cameraActive: Boolean,
    @SerializedName("notification_active") val notificationActive: Boolean
)

data class UpdateDeviceRequest(
    @SerializedName("user_device") val userDevice: UserDeviceUpdate
)
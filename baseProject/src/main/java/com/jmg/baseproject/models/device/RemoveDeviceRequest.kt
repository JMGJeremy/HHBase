package com.jmg.baseproject.models.device

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class RemoveDeviceRequest(
    @SerializedName("firebase_key")
    val firebaseKey: String
)
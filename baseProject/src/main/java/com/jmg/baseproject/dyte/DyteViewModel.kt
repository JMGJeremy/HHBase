package com.jmg.baseproject.dyte

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.dyte.core.DyteMeetingBuilder
import io.dyte.core.DyteMobileClient
import io.dyte.core.platform.VideoView
import io.dyte.core.listeners.DyteParticipantEventsListener
import io.dyte.core.listeners.DyteSelfEventsListener
import io.dyte.core.models.DyteJoinedMeetingParticipant
import io.dyte.core.models.DyteMeetingInfoV2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DyteViewModel(): ViewModel() {

    private val TAG = "DyteViewModel"

    val _dyteError = MutableStateFlow("")
    val dyteError: StateFlow<String?> = _dyteError

    val _loading = MutableStateFlow<String?>(null)
    val loading: StateFlow<String?> = _loading

    val _client = MutableStateFlow<DyteMobileClient?>(null)
    val client: StateFlow<DyteMobileClient?> = _client

    val _initialized = MutableStateFlow(false)
    val initialized: StateFlow<Boolean> = _initialized

    var dyteMeetingInfo: DyteMeetingInfoV2? = null

    fun setMeetingInfo(token: String, enableVideo: Boolean, enableAudio: Boolean){
        val info = DyteMeetingInfoV2(
            authToken = token,
            enableVideo = enableVideo,
            enableAudio = enableAudio,
            baseUrl = "dyte.i"
        )
        dyteMeetingInfo = info
    }

    fun startMeeting(context: Activity, dyteMeetingInfo: DyteMeetingInfoV2){
        val b = DyteMeetingBuilder.build(context)
        b.init(
            dyteMeetingInfo = dyteMeetingInfo,
            onInitCompleted = {
                _initialized.update { true }
        }, onInitFailed = {
                _initialized.update { false }
            _dyteError.update {
                "Failed to initialize Dyte"
            }
        })
        _client.update {
            b
        }
    }

    fun startParticipantListener(listener: DyteParticipantEventsListener) {
        _client.value?.addParticipantEventsListener(listener)
    }

    fun startLocalListener(listener: DyteSelfEventsListener) {
        _client.value?.addSelfEventsListener(listener)
    }

    fun leaveRoom(){
        _client.value?.leaveRoom()
    }

}
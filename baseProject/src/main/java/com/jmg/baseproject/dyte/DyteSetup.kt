package com.jmg.baseproject.dyte

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import io.dyte.core.DyteMeetingBuilder
import io.dyte.core.DyteMobileClient
import io.dyte.core.models.DyteMeetingInfoV2
import com.jmg.baseproject.R
import io.dyte.core.VideoView
import io.dyte.core.listeners.DyteParticipantEventsListener
import io.dyte.core.listeners.DyteSelfEventsListener

class DyteSetup: AppCompatActivity() {

    private val TAG = "DyteSetup"

    private lateinit var dyteClient: DyteMobileClient
    private lateinit var dyteLocalVideo: VideoView

    private var name: String? = ""
    private var meetingStarted by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ){
                if (meetingStarted) {
                    AndroidView(
                        factory = {
                            dyteLocalVideo
                        }
                    )
                } else {
                    CircularProgressIndicator()
                }
            }
        }

        dyteClient = DyteMeetingBuilder.build(this)

        val dyteToken = intent.extras?.getString("dyteToken")
        name = intent.extras?.getString("name")

        Log.e(TAG, "token = $dyteToken name = $name")

        val meetingInfo = DyteMeetingInfoV2(
            authToken = dyteToken ?: "",
            enableAudio = true,
            enableVideo = true,
        )

        dyteClient.init(meetingInfo,
            onInitCompleted = {
                joinRoom()
                Log.e(TAG, "dyte init success")
                dyteLocalVideo = dyteClient.localUser.getVideoView() ?: VideoView(context = this@DyteSetup)
            },
            onInitFailed = {
                Log.e(TAG, "dyte init failed")
            })
    }

    fun joinRoom(){
        dyteClient.joinRoom(
            onSuccess = {
                Log.e(TAG, "dyte join success")
                meetingStarted = true
                dyteClient.addSelfEventsListener(object: DyteSelfEventsListener{
                    override fun onVideoUpdate(videoEnabled: Boolean) {
                        super.onVideoUpdate(videoEnabled)
                        if (videoEnabled){
                            dyteClient.localUser.getVideoView()
                        } else {

                        }
                    }

                    override fun onAudioUpdate(audioEnabled: Boolean) {
                        super.onAudioUpdate(audioEnabled)
                        if (audioEnabled) {

                        } else {

                        }
                    }
                })
            },
            onFailed = {
                Log.e(TAG, "dyte join failed")
            }
        )
    }

    fun leaveRoom(){
        dyteClient.leaveRoom(
            onFailed = {

            },
            onSuccess = {

            }
        )
    }
}
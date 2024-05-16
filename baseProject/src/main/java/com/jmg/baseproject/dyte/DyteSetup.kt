package com.jmg.baseproject.dyte

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import io.dyte.core.DyteMeetingBuilder
import io.dyte.core.DyteMobileClient
import io.dyte.core.VideoView
import io.dyte.core.listeners.DyteParticipantEventsListener
import io.dyte.core.listeners.DyteSelfEventsListener
import io.dyte.core.models.DyteJoinedMeetingParticipant
import io.dyte.core.models.DyteMeetingInfoV2

class DyteSetup: AppCompatActivity() {

    private val TAG = "DyteSetup"

    private lateinit var dyteClient: DyteMobileClient
    private lateinit var dyteLocalVideo: VideoView

    private var name: String? = ""
    private var meetingStarted by mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screen = LocalConfiguration.current
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Green),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if (meetingStarted) {
                    AndroidView(
                        factory = {
                            if (::dyteLocalVideo.isInitialized) {
                                dyteLocalVideo
                            } else {
                                View(context)
                            }
                        },
                        modifier = Modifier
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
        Log.e(TAG, "meeting info = $meetingInfo")

        dyteClient.init(meetingInfo,
            onInitCompleted = {
                joinRoom()
                Log.e(TAG, "dyte init success")
                dyteLocalVideo = dyteClient.localUser.getVideoView() ?: VideoView(context = this@DyteSetup)
            },
            onInitFailed = {
                finish()
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
                        Log.e(TAG, "video update = $videoEnabled")
                        if (videoEnabled){
                            dyteClient.localUser.getVideoView()
                            dyteClient.participants.joined[0].getVideoView()
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

                dyteClient.addParticipantEventsListener(object: DyteParticipantEventsListener{
                    override fun onParticipantJoin(participant: DyteJoinedMeetingParticipant) {
                        super.onParticipantJoin(participant)
                        Log.e(TAG, "participant join $participant")
                        dyteLocalVideo = participant.getVideoView() ?: VideoView(this@DyteSetup)

//                        dyteRemoteVideo = participant.getVideoView() ?: VideoView(this@DyteSetup)
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
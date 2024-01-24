package com.jmg.baseproject.dyte

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

    private lateinit var dyteClient: DyteMobileClient
    private lateinit var dyteLocalVideo: VideoView

    private var name: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ){
                AndroidView(
                    factory = {
                        dyteClient.localUser.getVideoView() ?: VideoView(context = this@DyteSetup)
                    }
                )
            }
        }

        dyteClient = DyteMeetingBuilder.build(this)
        dyteLocalVideo = this.findViewById(R.id.dyte_local_video)

        val dyteToken = intent.extras?.getString("dyteToken")
        name = intent.extras?.getString("name")

        val meetingInfo = DyteMeetingInfoV2(
            authToken = dyteToken ?: "",
            enableAudio = true,
            enableVideo = true,
        )

        dyteClient.init(meetingInfo,
            onInitCompleted = {
                joinRoom()
            },
            onInitFailed = {

            })
    }

    fun joinRoom(){
        dyteClient.joinRoom(
            onSuccess = {
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
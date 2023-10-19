package com.jmg.baseproject.twilio

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.twilio.video.AudioCodec
import com.twilio.video.ConnectOptions
import com.twilio.video.LocalAudioTrack
import com.twilio.video.OpusCodec
import com.twilio.video.RemoteAudioTrack
import com.twilio.video.RemoteAudioTrackPublication
import com.twilio.video.RemoteDataTrack
import com.twilio.video.RemoteDataTrackPublication
import com.twilio.video.RemoteParticipant
import com.twilio.video.RemoteVideoTrack
import com.twilio.video.RemoteVideoTrackPublication
import com.twilio.video.Room
import com.twilio.video.TwilioException
import com.twilio.video.Video
import com.twilio.video.VideoTrack
import com.twilio.video.VideoView
import java.util.Collections

class TwilioRoom(
    context:Context,
    prog: MutableState<Boolean>
):ViewModel() {

    private val TAG = "CallViewModel"

    private var audioCodec: AudioCodec? = OpusCodec()
    private val LOCAL_AUDIO_TRACK_NAME = "mic"
    private var remoteParticipantIdentity: String = ""

    var otherPartyConnected = mutableStateOf(false)

    val callState = mutableStateOf(CallStatus.NOT_STARTED)
    val videoView = mutableStateOf<VideoView?>(VideoView(context))
    private var localAudioTrack: LocalAudioTrack? = null

    var mRoom: Room? = null
    private var progress = prog

    fun muteAudio(){
        if (localAudioTrack != null) {
            val enable = !localAudioTrack!!.isEnabled
            localAudioTrack!!.enable(enable)
        }
    }

    fun connectToRoom(context: Context, roomName: String, accessToken: String) {
        callState.value = CallStatus.CONNECTING
        progress.value = true

        localAudioTrack = LocalAudioTrack.create(context, true, LOCAL_AUDIO_TRACK_NAME)

        val connectOptions = ConnectOptions.Builder(accessToken)
            .roomName(roomName)
            .audioTracks(Collections.singletonList(localAudioTrack))
            .preferAudioCodecs(Collections.singletonList(audioCodec))
            .enableAutomaticSubscription(true)// Enable audio track
            .build()

        mRoom = Video.connect(context, connectOptions, object : Room.Listener {
            override fun onConnected(room: Room) {
                progress.value = false
                Log.e(TAG, "onConnected")
                callState.value = CallStatus.CONNECTED
            }

            override fun onConnectFailure(room: Room, twilioException: TwilioException) {
                progress.value = false
                Log.e(TAG, "onConnectFailure ${twilioException.localizedMessage}")
                callState.value = CallStatus.CONNECT_FAILURE
            }

            override fun onReconnecting(room: Room, twilioException: TwilioException) {
                progress.value = true
                Log.e(TAG, "onReconnecting")
                callState.value = CallStatus.RECONNECTING
            }

            override fun onReconnected(room: Room) {
                progress.value = false
                Log.e(TAG, "onReconnected")
                callState.value = CallStatus.RECONNECTED
            }

            override fun onDisconnected(room: Room, twilioException: TwilioException?) {
                progress.value = false
                Log.e(TAG, "onDisconnected")
                // Only reinitialize the UI if disconnect was not called from onDestroy()

                cleanUpAfterDisconnect();


                callState.value = CallStatus.DISCONNECTED
            }

            override fun onParticipantConnected(room: Room, remoteParticipant: RemoteParticipant) {
                progress.value = false
                Log.e(TAG, "onParticipantConnected")
                addRemoteParticipant(remoteParticipant)
                callState.value = CallStatus.PARTICIPANT_CONNECTED
            }

            override fun onParticipantDisconnected(
                room: Room,
                remoteParticipant: RemoteParticipant
            ) {
                progress.value = false
                Log.e(TAG, "onParticipantDisconnected")
                otherPartyConnected.value = false
                callState.value = CallStatus.PARTICIPANT_DISCONNECTED
            }

            override fun onRecordingStarted(room: Room) {
                progress.value = false
                callState.value = CallStatus.RECORDING_STARTED
                Log.e(TAG, "onRecordingStarted")
            }

            override fun onRecordingStopped(room: Room) {
                progress.value = false
                Log.e(TAG, "onRecordingStopped")
                callState.value = CallStatus.RECORDING_STOPPED
            }

        })
    }

    fun disconnectRoom(){
//        callManager.disconnectRoom()
//        cleanUpAfterDisconnect()

        //TODO: DISCONNECT
//        videoView.value = null
    }

    @SuppressLint("SetTextI18n")
    private fun addRemoteParticipant(remoteParticipant: RemoteParticipant) {
        if (remoteParticipantIdentity.isEmpty()) {
            otherPartyConnected.value = true
            remoteParticipantIdentity = remoteParticipant.identity
            if (remoteParticipant.remoteVideoTracks.size > 0) {
                val remoteVideoTrackPublication = remoteParticipant.remoteVideoTracks[0]
                if (remoteVideoTrackPublication.isTrackSubscribed) {
                    addRemoteParticipantVideo(remoteVideoTrackPublication.remoteVideoTrack)
                }
            }
            remoteParticipant.setListener(remoteParticipantListener())
        }
    }


    private fun addRemoteParticipantVideo(videoTrack: RemoteVideoTrack?) {
//        calling_lawyer.setVisibility(View.GONE)
//        isConnectingToLawyer = false
//        moveLocalVideoToThumbnailView()
        videoView.value?.setMirror(true)
        videoTrack?.addSink(videoView.value!!)
    }


    @SuppressLint("SetTextI18n")
    private fun remoteParticipantListener(): RemoteParticipant.Listener? {
        return object : RemoteParticipant.Listener {
            override fun onAudioTrackPublished(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication
            ) {
                Log.i(
                    TAG, String.format(
                        "onAudioTrackPublished: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteAudioTrackPublication: sid=%s, enabled=%b, " +
                                "subscribed=%b, name=%s]",
                        remoteParticipant.identity,
                        remoteAudioTrackPublication.trackSid,
                        remoteAudioTrackPublication.isTrackEnabled,
                        remoteAudioTrackPublication.isTrackSubscribed,
                        remoteAudioTrackPublication.trackName
                    )
                )
            }

            override fun onAudioTrackUnpublished(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication
            ) {
                Log.i(
                    TAG, String.format(
                        ("onAudioTrackUnpublished: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteAudioTrackPublication: sid=%s, enabled=%b, " +
                                "subscribed=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteAudioTrackPublication.trackSid,
                        remoteAudioTrackPublication.isTrackEnabled,
                        remoteAudioTrackPublication.isTrackSubscribed,
                        remoteAudioTrackPublication.trackName
                    )
                )
            }

            override fun onDataTrackPublished(
                remoteParticipant: RemoteParticipant,
                remoteDataTrackPublication: RemoteDataTrackPublication
            ) {
                Log.i(
                    TAG, String.format(
                        ("onDataTrackPublished: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteDataTrackPublication: sid=%s, enabled=%b, " +
                                "subscribed=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteDataTrackPublication.trackSid,
                        remoteDataTrackPublication.isTrackEnabled,
                        remoteDataTrackPublication.isTrackSubscribed,
                        remoteDataTrackPublication.trackName
                    )
                )
            }

            override fun onDataTrackUnpublished(
                remoteParticipant: RemoteParticipant,
                remoteDataTrackPublication: RemoteDataTrackPublication
            ) {
                Log.i(
                    TAG, String.format(
                        ("onDataTrackUnpublished: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteDataTrackPublication: sid=%s, enabled=%b, " +
                                "subscribed=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteDataTrackPublication.trackSid,
                        remoteDataTrackPublication.isTrackEnabled,
                        remoteDataTrackPublication.isTrackSubscribed,
                        remoteDataTrackPublication.trackName
                    )
                )
            }

            override fun onVideoTrackPublished(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication
            ) {
                Log.i(
                    TAG, String.format(
                        ("onVideoTrackPublished: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteVideoTrackPublication: sid=%s, enabled=%b, " +
                                "subscribed=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteVideoTrackPublication.trackSid,
                        remoteVideoTrackPublication.isTrackEnabled,
                        remoteVideoTrackPublication.isTrackSubscribed,
                        remoteVideoTrackPublication.trackName
                    )
                )
            }

            override fun onVideoTrackUnpublished(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication
            ) {
                Log.i(
                    TAG, String.format(
                        ("onVideoTrackUnpublished: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteVideoTrackPublication: sid=%s, enabled=%b, " +
                                "subscribed=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteVideoTrackPublication.trackSid,
                        remoteVideoTrackPublication.isTrackEnabled,
                        remoteVideoTrackPublication.isTrackSubscribed,
                        remoteVideoTrackPublication.trackName
                    )
                )
            }

            override fun onAudioTrackSubscribed(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication,
                remoteAudioTrack: RemoteAudioTrack
            ) {
                Log.i(
                    TAG, String.format(
                        ("onAudioTrackSubscribed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteAudioTrack: enabled=%b, playbackEnabled=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteAudioTrack.isEnabled,
                        remoteAudioTrack.isPlaybackEnabled,
                        remoteAudioTrack.name
                    )
                )
            }

            override fun onAudioTrackUnsubscribed(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication,
                remoteAudioTrack: RemoteAudioTrack
            ) {
                Log.i(
                    TAG, String.format(
                        ("onAudioTrackUnsubscribed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteAudioTrack: enabled=%b, playbackEnabled=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteAudioTrack.isEnabled,
                        remoteAudioTrack.isPlaybackEnabled,
                        remoteAudioTrack.name
                    )
                )
            }

            override fun onAudioTrackSubscriptionFailed(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication,
                twilioException: TwilioException
            ) {
                Log.i(
                    TAG, String.format(
                        ("onAudioTrackSubscriptionFailed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteAudioTrackPublication: sid=%b, name=%s]" +
                                "[TwilioException: code=%d, message=%s]"),
                        remoteParticipant.identity,
                        remoteAudioTrackPublication.trackSid,
                        remoteAudioTrackPublication.trackName,
                        twilioException.code,
                        twilioException.message
                    )
                )
            }

            override fun onDataTrackSubscribed(
                remoteParticipant: RemoteParticipant,
                remoteDataTrackPublication: RemoteDataTrackPublication,
                remoteDataTrack: RemoteDataTrack
            ) {
                Log.i(
                    TAG, String.format(
                        ("onDataTrackSubscribed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteDataTrack: enabled=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteDataTrack.isEnabled,
                        remoteDataTrack.name
                    )
                )
            }

            override fun onDataTrackUnsubscribed(
                remoteParticipant: RemoteParticipant,
                remoteDataTrackPublication: RemoteDataTrackPublication,
                remoteDataTrack: RemoteDataTrack
            ) {
                Log.i(
                    TAG, String.format(
                        ("onDataTrackUnsubscribed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteDataTrack: enabled=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteDataTrack.isEnabled,
                        remoteDataTrack.name
                    )
                )
            }

            override fun onDataTrackSubscriptionFailed(
                remoteParticipant: RemoteParticipant,
                remoteDataTrackPublication: RemoteDataTrackPublication,
                twilioException: TwilioException
            ) {
                Log.i(
                    TAG, String.format(
                        ("onDataTrackSubscriptionFailed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteDataTrackPublication: sid=%b, name=%s]" +
                                "[TwilioException: code=%d, message=%s]"),
                        remoteParticipant.identity,
                        remoteDataTrackPublication.trackSid,
                        remoteDataTrackPublication.trackName,
                        twilioException.code,
                        twilioException.message
                    )
                )
            }

            override fun onVideoTrackSubscribed(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication,
                remoteVideoTrack: RemoteVideoTrack
            ) {
                Log.i(
                    TAG, String.format(
                        ("onVideoTrackSubscribed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteVideoTrack: enabled=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteVideoTrack.isEnabled,
                        remoteVideoTrack.name
                    )
                )
                addRemoteParticipantVideo(remoteVideoTrack)
            }

            override fun onVideoTrackUnsubscribed(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication,
                remoteVideoTrack: RemoteVideoTrack
            ) {
                Log.i(
                    TAG, String.format(
                        ("onVideoTrackUnsubscribed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteVideoTrack: enabled=%b, name=%s]"),
                        remoteParticipant.identity,
                        remoteVideoTrack.isEnabled,
                        remoteVideoTrack.name
                    )
                )
                removeParticipantVideo(remoteVideoTrack)
            }

            override fun onVideoTrackSubscriptionFailed(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication,
                twilioException: TwilioException
            ) {
                Log.i(
                    TAG, String.format(
                        ("onVideoTrackSubscriptionFailed: " +
                                "[RemoteParticipant: identity=%s], " +
                                "[RemoteVideoTrackPublication: sid=%b, name=%s]" +
                                "[TwilioException: code=%d, message=%s]"),
                        remoteParticipant.identity,
                        remoteVideoTrackPublication.trackSid,
                        remoteVideoTrackPublication.trackName,
                        twilioException.code,
                        twilioException.message
                    )
                )
                //                Snackbar.make(connectActionFab,
//                        String.format("Failed to subscribe to %s video track",
//                                remoteParticipant.getIdentity()),
//                        Snackbar.LENGTH_LONG)
//                        .show();
            }

            override fun onAudioTrackEnabled(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication
            ) {
            }

            override fun onAudioTrackDisabled(
                remoteParticipant: RemoteParticipant,
                remoteAudioTrackPublication: RemoteAudioTrackPublication
            ) {
            }

            override fun onVideoTrackEnabled(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication
            ) {
            }

            override fun onVideoTrackDisabled(
                remoteParticipant: RemoteParticipant,
                remoteVideoTrackPublication: RemoteVideoTrackPublication
            ) {
            }
        }
    }

    private fun removeParticipantVideo(videoTrack: VideoTrack) {
        videoTrack.removeSink(videoView.value!!)
        videoView.value = null
    }

    override fun onCleared() {
        super.onCleared()

        cleanUpAfterDisconnect()
    }

    fun cleanUpAfterDisconnect(){
        if (mRoom != null && mRoom!!.state != Room.State.DISCONNECTED) {
            mRoom?.disconnect()
            mRoom = null
//            disconnectedFromOnDestroy = true
        }
        if (localAudioTrack != null) {
            localAudioTrack!!.release()
            localAudioTrack = null
        }
    }
}

enum class CallStatus{
    NOT_STARTED,
    CONNECTING,
    CONNECTED,
    CONNECT_FAILURE,
    RECONNECTING,
    RECONNECTED,
    DISCONNECTED,
    PARTICIPANT_CONNECTED,
    PARTICIPANT_DISCONNECTED,
    RECORDING_STARTED,
    RECORDING_STOPPED,
}
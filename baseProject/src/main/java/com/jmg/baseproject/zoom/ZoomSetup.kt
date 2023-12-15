package com.jmg.baseproject.zoom

import android.content.Context
import us.zoom.sdk.ZoomVideoSDK
import us.zoom.sdk.ZoomVideoSDKAnnotationHelper
import us.zoom.sdk.ZoomVideoSDKAudioHelper
import us.zoom.sdk.ZoomVideoSDKAudioRawData
import us.zoom.sdk.ZoomVideoSDKCRCCallStatus
import us.zoom.sdk.ZoomVideoSDKChatHelper
import us.zoom.sdk.ZoomVideoSDKChatMessage
import us.zoom.sdk.ZoomVideoSDKChatMessageDeleteType
import us.zoom.sdk.ZoomVideoSDKChatPrivilegeType
import us.zoom.sdk.ZoomVideoSDKDelegate
import us.zoom.sdk.ZoomVideoSDKInitParams
import us.zoom.sdk.ZoomVideoSDKLiveStreamHelper
import us.zoom.sdk.ZoomVideoSDKLiveStreamStatus
import us.zoom.sdk.ZoomVideoSDKLiveTranscriptionHelper
import us.zoom.sdk.ZoomVideoSDKMultiCameraStreamStatus
import us.zoom.sdk.ZoomVideoSDKNetworkStatus
import us.zoom.sdk.ZoomVideoSDKPasswordHandler
import us.zoom.sdk.ZoomVideoSDKPhoneFailedReason
import us.zoom.sdk.ZoomVideoSDKPhoneStatus
import us.zoom.sdk.ZoomVideoSDKProxySettingHandler
import us.zoom.sdk.ZoomVideoSDKRawDataPipe
import us.zoom.sdk.ZoomVideoSDKRecordingConsentHandler
import us.zoom.sdk.ZoomVideoSDKRecordingStatus
import us.zoom.sdk.ZoomVideoSDKSSLCertificateInfo
import us.zoom.sdk.ZoomVideoSDKShareHelper
import us.zoom.sdk.ZoomVideoSDKShareStatus
import us.zoom.sdk.ZoomVideoSDKUser
import us.zoom.sdk.ZoomVideoSDKUserHelper
import us.zoom.sdk.ZoomVideoSDKVideoCanvas
import us.zoom.sdk.ZoomVideoSDKVideoHelper
import us.zoom.sdk.ZoomVideoSDKVideoSubscribeFailReason
import us.zoom.sdk.ZoomVideoSDKVideoView

class ZoomSetup {

    fun zoomParams(url: String): ZoomVideoSDKInitParams {
        return ZoomVideoSDKInitParams().apply {
            domain = url
            enableLog = true
        }
    }

    fun zoomSdkInit(): ZoomVideoSDK {
        return ZoomVideoSDK.getInstance()
    }

    fun zoomListener(): ZoomVideoSDKDelegate {
        return object: ZoomVideoSDKDelegate{
            override fun onSessionJoin() {

            }

            override fun onSessionLeave() {

            }

            override fun onError(errorCode: Int) {

            }

            override fun onUserJoin(
                userHelper: ZoomVideoSDKUserHelper?,
                userList: MutableList<ZoomVideoSDKUser>?
            ) {

            }

            override fun onUserLeave(
                userHelper: ZoomVideoSDKUserHelper?,
                userList: MutableList<ZoomVideoSDKUser>?
            ) {

            }

            override fun onUserVideoStatusChanged(
                videoHelper: ZoomVideoSDKVideoHelper?,
                userList: MutableList<ZoomVideoSDKUser>?
            ) {

            }

            override fun onUserAudioStatusChanged(
                audioHelper: ZoomVideoSDKAudioHelper?,
                userList: MutableList<ZoomVideoSDKUser>?
            ) {

            }

            override fun onUserShareStatusChanged(
                shareHelper: ZoomVideoSDKShareHelper?,
                userInfo: ZoomVideoSDKUser?,
                status: ZoomVideoSDKShareStatus?
            ) {

            }

            override fun onLiveStreamStatusChanged(
                liveStreamHelper: ZoomVideoSDKLiveStreamHelper?,
                status: ZoomVideoSDKLiveStreamStatus?
            ) {

            }

            override fun onChatNewMessageNotify(
                chatHelper: ZoomVideoSDKChatHelper?,
                messageItem: ZoomVideoSDKChatMessage?
            ) {

            }

            override fun onChatDeleteMessageNotify(
                chatHelper: ZoomVideoSDKChatHelper?,
                msgID: String?,
                deleteBy: ZoomVideoSDKChatMessageDeleteType?
            ) {

            }

            override fun onChatPrivilegeChanged(
                chatHelper: ZoomVideoSDKChatHelper?,
                currentPrivilege: ZoomVideoSDKChatPrivilegeType?
            ) {

            }

            override fun onUserHostChanged(
                userHelper: ZoomVideoSDKUserHelper?,
                userInfo: ZoomVideoSDKUser?
            ) {

            }

            override fun onUserManagerChanged(user: ZoomVideoSDKUser?) {

            }

            override fun onUserNameChanged(user: ZoomVideoSDKUser?) {

            }

            override fun onUserActiveAudioChanged(
                audioHelper: ZoomVideoSDKAudioHelper?,
                list: MutableList<ZoomVideoSDKUser>?
            ) {

            }

            override fun onSessionNeedPassword(handler: ZoomVideoSDKPasswordHandler?) {

            }

            override fun onSessionPasswordWrong(handler: ZoomVideoSDKPasswordHandler?) {

            }

            override fun onMixedAudioRawDataReceived(rawData: ZoomVideoSDKAudioRawData?) {

            }

            override fun onOneWayAudioRawDataReceived(
                rawData: ZoomVideoSDKAudioRawData?,
                user: ZoomVideoSDKUser?
            ) {

            }

            override fun onShareAudioRawDataReceived(rawData: ZoomVideoSDKAudioRawData?) {

            }

            override fun onCommandReceived(sender: ZoomVideoSDKUser?, strCmd: String?) {

            }

            override fun onCommandChannelConnectResult(isSuccess: Boolean) {

            }

            override fun onCloudRecordingStatus(
                status: ZoomVideoSDKRecordingStatus?,
                handler: ZoomVideoSDKRecordingConsentHandler?
            ) {

            }

            override fun onHostAskUnmute() {

            }

            override fun onInviteByPhoneStatus(
                status: ZoomVideoSDKPhoneStatus?,
                reason: ZoomVideoSDKPhoneFailedReason?
            ) {

            }

            override fun onMultiCameraStreamStatusChanged(
                status: ZoomVideoSDKMultiCameraStreamStatus?,
                user: ZoomVideoSDKUser?,
                videoPipe: ZoomVideoSDKRawDataPipe?
            ) {

            }

            override fun onMultiCameraStreamStatusChanged(
                status: ZoomVideoSDKMultiCameraStreamStatus?,
                user: ZoomVideoSDKUser?,
                canvas: ZoomVideoSDKVideoCanvas?
            ) {

            }

            override fun onLiveTranscriptionStatus(status: ZoomVideoSDKLiveTranscriptionHelper.ZoomVideoSDKLiveTranscriptionStatus?) {

            }

            @Deprecated("Deprecated in Java")
            override fun onLiveTranscriptionMsgReceived(
                ltMsg: String?,
                pUser: ZoomVideoSDKUser?,
                type: ZoomVideoSDKLiveTranscriptionHelper.ZoomVideoSDKLiveTranscriptionOperationType?
            ) {

            }

            override fun onOriginalLanguageMsgReceived(messageInfo: ZoomVideoSDKLiveTranscriptionHelper.ILiveTranscriptionMessageInfo?) {

            }

            override fun onLiveTranscriptionMsgInfoReceived(messageInfo: ZoomVideoSDKLiveTranscriptionHelper.ILiveTranscriptionMessageInfo?) {

            }

            override fun onLiveTranscriptionMsgError(
                spokenLanguage: ZoomVideoSDKLiveTranscriptionHelper.ILiveTranscriptionLanguage?,
                transcriptLanguage: ZoomVideoSDKLiveTranscriptionHelper.ILiveTranscriptionLanguage?
            ) {

            }

            override fun onProxySettingNotification(handler: ZoomVideoSDKProxySettingHandler?) {

            }

            override fun onSSLCertVerifiedFailNotification(info: ZoomVideoSDKSSLCertificateInfo?) {

            }

            override fun onCameraControlRequestResult(
                user: ZoomVideoSDKUser?,
                isApproved: Boolean
            ) {

            }

            override fun onUserVideoNetworkStatusChanged(
                status: ZoomVideoSDKNetworkStatus?,
                user: ZoomVideoSDKUser?
            ) {

            }

            override fun onUserRecordingConsent(user: ZoomVideoSDKUser?) {

            }

            override fun onCallCRCDeviceStatusChanged(status: ZoomVideoSDKCRCCallStatus?) {

            }

            override fun onVideoCanvasSubscribeFail(
                fail_reason: ZoomVideoSDKVideoSubscribeFailReason?,
                pUser: ZoomVideoSDKUser?,
                view: ZoomVideoSDKVideoView?
            ) {

            }

            override fun onShareCanvasSubscribeFail(
                fail_reason: ZoomVideoSDKVideoSubscribeFailReason?,
                pUser: ZoomVideoSDKUser?,
                view: ZoomVideoSDKVideoView?
            ) {

            }

            override fun onAnnotationHelperCleanUp(helper: ZoomVideoSDKAnnotationHelper?) {

            }

            override fun onAnnotationPrivilegeChange(
                enable: Boolean,
                shareOwner: ZoomVideoSDKUser?
            ) {

            }

        }
    }
}
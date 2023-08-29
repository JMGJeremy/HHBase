package com.jmg.baseproject.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.util.Consumer
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VideoScreen(
    facingFront: Boolean = true,
    onImageCaptured: (Uri, Boolean) -> Unit,
    maxTime: Long
) {
    val TAG = "VideoScreen"
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var permissionsApproved by remember { mutableStateOf(false)}
    val permissionList = if (Build.VERSION.SDK_INT < 32){
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }else{
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    }
    val permissionState = rememberMultiplePermissionsState(
        permissions = permissionList,
        onPermissionsResult = {
            if (!it.values.contains(false)){
                permissionsApproved = true
            }
        }
    )

    var recording: Recording? = remember { null }
    val previewView : PreviewView = remember { PreviewView(context) }
    var videoCapture: MutableState<VideoCapture<Recorder>?> = remember { mutableStateOf(null)}
    var recordingStart = remember { mutableStateOf(false)}
    val audioEnable = remember { mutableStateOf(false)}
    val cameraSelector = remember {
        mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA)
    }

    LaunchedEffect(Unit){
        permissionState.launchMultiplePermissionRequest()
    }

    LaunchedEffect(previewView){
        videoCapture.value = context.createVideoCaptureUseCase(
            lifeCycleOwner = lifecycleOwner,
            cameraSelector = cameraSelector.value,
            previewView = previewView,
            context = context
        )
        Log.e(TAG, "video capture = ${videoCapture.value}")
    }

    if (permissionsApproved){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            AndroidView(
                factory = {
                    previewView
                },
                modifier = Modifier
                    .fillMaxSize()
            )
            IconButton(
                onClick = {
                    if (!recordingStart.value) {
                        Toast.makeText(context, "start", Toast.LENGTH_LONG).show()
                        videoCapture.value?.let { videoCapture ->
                            recordingStart.value = true
                            val mediaDir = context.getExternalFilesDir(null)?.let {
                                File(it, context.getString(R.string.app_name)).apply { it.mkdirs() }
                            }

                            Log.e(TAG, "mediaDir = ${mediaDir?.path}")
                            if (mediaDir != null) {
                                recording = startVideoRecording(
                                    context = context,
                                    videoCapture = videoCapture,
                                    outputDirectory = mediaDir,
                                    executor = ContextCompat.getMainExecutor(context),
                                    audioEnable = audioEnable.value
                                ) { event ->
                                    Log.e(TAG, "event = $event")
                                    if (event is VideoRecordEvent.Finalize) {
                                        val uri = event.outputResults.outputUri
                                        Log.e(
                                            TAG,
                                            "output results = ${event.outputResults} uri = ${uri}"
                                        )
                                        if (uri != Uri.EMPTY) {
                                            val uriEncoded = URLEncoder.encode(
                                                uri.toString(),
                                                StandardCharsets.UTF_8.toString()
                                            )
                                            Log.e(TAG, "uri = $uriEncoded")
                                            onImageCaptured.invoke(uri, true)
                                        }
                                    }
                                }

                                Handler(Looper.getMainLooper()).postDelayed({
                                    recordingStart.value = false
                                    recording?.stop()
                                    recording = null
                                    Toast.makeText(context, "Stopped", Toast.LENGTH_LONG).show()
                                }, maxTime)
                            }else{
                                Toast.makeText(context, "dir null", Toast.LENGTH_LONG).show()
                            }
                            Log.e(TAG, "recording = $recording")
                        }

                    } else {
                        Toast.makeText(context, "stop", Toast.LENGTH_LONG).show()
                        recordingStart.value = false
                        recording?.stop()
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 16.dp),
                content = {
                    Image(
                        painter = painterResource(R.drawable.bars),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                    )
                }
            )
        }
    }

}

suspend fun Context.createVideoCaptureUseCase(
    lifeCycleOwner: LifecycleOwner,
    cameraSelector: CameraSelector,
    previewView: PreviewView,
    context: Context
): VideoCapture<Recorder>{
    val TAG = "CreateVideoCaptureUseCase"
    val preview = Preview.Builder()
        .build()
        .apply{
            setSurfaceProvider(previewView.surfaceProvider)
        }
    Log.e(TAG, "preview = $preview")
    val qualitySelector = QualitySelector.from(
        Quality.SD,
        FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
    )

    val recorder = Recorder.Builder()
        .setExecutor(ContextCompat.getMainExecutor(context))
        .setQualitySelector(qualitySelector)
        .build()

    val videoCapture = VideoCapture.withOutput(recorder)

    val cameraProvider = getCameraProvider()
//    cameraProvider.unbindAll()
    cameraProvider.bindToLifecycle(
        lifeCycleOwner,
        CameraSelector.DEFAULT_FRONT_CAMERA,
        preview,
        videoCapture
    )
    return videoCapture
}

@SuppressLint("MissingPermission")
fun startVideoRecording(
    context: Context,
    videoCapture: VideoCapture<Recorder>,
    outputDirectory: File,
    executor: Executor,
    audioEnable: Boolean,
    consumer: Consumer<VideoRecordEvent>
): Recording? {

    val TAG = "StartRecording"
    try {
        if (!outputDirectory.exists()){
            if (!outputDirectory.mkdirs()) {
                Log.e(TAG, "Failed to create output directory: ${outputDirectory.absolutePath}")
            }
        }

        val videoFile = File(
            outputDirectory,
            System.currentTimeMillis().toString() + ".mp4"
        )

        val outputOptions = FileOutputOptions.Builder(videoFile).build()
        return videoCapture.output
            .prepareRecording(context, outputOptions)
            .apply { if (audioEnable) withAudioEnabled() }
            .start(executor, consumer)
    } catch (e: Exception) {
        Log.e(TAG, "Error starting video recording: ${e.message}")
        e.printStackTrace()
        // Handle the error as needed
        return null // Or whatever is appropriate in your case
    }
}


@androidx.compose.ui.tooling.preview.Preview
@Composable
fun VideoPrev(){
    HHBaseTheme {
//        VideoScreen()
    }
}

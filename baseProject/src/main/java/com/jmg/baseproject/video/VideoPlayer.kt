package com.jmg.baseproject.video


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoPlayer(
    modifier: Modifier,
    uri: Uri
){
    val context =  LocalContext.current

    var playing by remember { mutableStateOf(false)}
    var muted by remember { mutableStateOf(false)}

    val padding = 16.dp


    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))

                setMediaSource(source)
                prepare()
            }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

                    player = exoPlayer
                }
            },
            modifier = Modifier
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            if (!muted) {
                Image(
                    painterResource(R.drawable.mute),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = padding)
                        .size(24.dp)
                        .clickable {
                                   muted = true
                            exoPlayer.volume = 0f
                        },
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }else{

                Image(
                    painterResource(R.drawable.volume_high),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = padding)
                        .size(24.dp)
                        .clickable {
                            muted = false
                            exoPlayer.volume = 1f
                        },
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }

            Image(
                painter = painterResource(R.drawable.backward),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        exoPlayer.seekTo(0L)
                    },
                colorFilter = ColorFilter.tint(Color.White)
            )

            if (!playing) {
                Image(
                    painter = painterResource(R.drawable.play),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = padding)
                        .size(24.dp)
                        .clickable {
                            exoPlayer.play()
                            playing = true
                        },
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }else{
                Image(
                    painter = painterResource(R.drawable.pause),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = padding)
                        .size(24.dp)
                        .clickable {
                            exoPlayer.pause()
                            playing = false
                        },
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
fun MediaPlayerPrev(){
    HHBaseTheme {
        VideoPlayer(
            modifier = Modifier,
            uri = Uri.EMPTY
        )
    }
}
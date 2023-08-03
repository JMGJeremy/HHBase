package com.jmg.baseproject.ui.images

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Logo(
    context:Context,
    logo: Int,
    modifier: Modifier
){

    Image(
        painter = rememberAsyncImagePainter(model = context.getDrawable(logo)),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
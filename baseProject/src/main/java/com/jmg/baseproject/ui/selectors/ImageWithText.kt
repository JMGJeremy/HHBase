package com.jmg.baseproject.ui.selectors

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageWithText(
    image: Int,
    text: String
){
    Box{
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun ImageWithTextPreview(){
    ImageWithText(
        image = android.R.drawable.ic_menu_camera,
        text = "Camera"
    )
}
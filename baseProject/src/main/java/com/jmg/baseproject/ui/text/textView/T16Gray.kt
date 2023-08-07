package com.jmg.baseproject.ui.text.textView

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun T16Gray(
    text: String,
    modifier: Modifier
){
    Text(text = text,
        modifier = modifier,
        color = Color.Gray,
        fontSize = 16.sp
        )
}

@Composable
fun T18Gray(
    text: String,
    modifier: Modifier
){
    Text(text = text,
        modifier = modifier,
        color = Color.Gray,
        fontSize = 18.sp
    )
}
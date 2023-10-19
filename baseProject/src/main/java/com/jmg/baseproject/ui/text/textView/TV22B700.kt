package com.jmg.baseproject.ui.text.textView

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TV22B700(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier,
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        color = MaterialTheme.colorScheme.inverseSurface
    )
}
@Composable
fun TV22B700Gray(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier,
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        color = Color.Gray
    )
}

@Composable
fun TV14Center(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}
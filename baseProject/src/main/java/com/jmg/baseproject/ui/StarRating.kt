package com.jmg.baseproject.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun StarRating(
    updateRating: (Int)->Unit,
    modifier: Modifier = Modifier
){
    var rating by remember { mutableStateOf(5) }

    Row(
        modifier = modifier
    ){
        for (i in 1..5) {
            Icon(
                imageVector = if (rating >= i){
                    Icons.Filled.Star
                } else {
                       Icons.Outlined.Star
                       },
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        rating = i
                        updateRating(i)
                    }
                    .padding(4.dp)
                    .size(24.dp),
                tint = if (rating >= i){
                    Color.Yellow
                } else {
                    Color.Gray
                }
            )
        }
    }
}

@Preview
@Composable
fun StarRatingPrev(){
    HHBaseTheme {
        StarRating(
            updateRating = {}
        )
    }
}
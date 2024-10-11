package com.jmg.baseproject.ui.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.R

@Composable
fun XBackButton(
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.xmark),
        contentDescription = "Back",
        modifier = Modifier
            .size(24.dp)
            .clickable(onClick = onClick),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
    )
}

@Preview
@Composable
fun PreviewXBackButton() {
    XBackButton(
        onClick = {}
    )
}
package com.jmg.baseproject.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ProgressDialogText(text: String) {
    Dialog(onDismissRequest = { }) {
        Column(
            Modifier
                .size(200.dp, 200.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.White
            )

            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
    }
}
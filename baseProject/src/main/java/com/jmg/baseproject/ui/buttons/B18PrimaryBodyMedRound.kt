package com.jmg.baseproject.ui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun B18PrimaryBodyMedRound(
    click: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle,
){

    Button(
        modifier = modifier
            .clickable { click.invoke() }
            .padding(top = 16.dp)
            .height(50.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(50)),
        enabled = enabled,
        onClick = {
            click.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
        ),
    ){
        Text(text = text,
            style = textStyle,

        )
    }
}

@Preview
@Composable
fun LoginButtonPreview(){

    HHBaseTheme {
        B18PrimaryBodyMedRound(
            click = {},
            text = "Submit",
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }

}
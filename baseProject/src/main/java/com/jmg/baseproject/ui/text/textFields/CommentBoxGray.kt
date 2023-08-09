package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun CommentBoxGray(
    value: MutableState<String?>,
    modifier: Modifier,
    label: String
){

    TextField(
        value = value.value ?: "",
        onValueChange = {
            value.value = it
        },
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledSupportingTextColor = Color.Gray,
            disabledTextColor = Color.Gray,
            errorSupportingTextColor = Color.Gray,
            errorTextColor = Color.Gray,
            focusedSupportingTextColor = Color.Gray,
            focusedTextColor = Color.Gray,
            unfocusedSupportingTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        label = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label,
                    color = Color.Gray
                )
            }
        },
    )

}

@Preview
@Composable
fun CommentBoxGrayPrev(){
    HHBaseTheme {
        CommentBoxGray(value = remember { mutableStateOf(null) },
        modifier = Modifier
            .border(
                width = .5.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .height(200.dp),
            label = "Enter optional stuff here"
        )
    }
}
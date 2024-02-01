package com.jmg.baseproject.ui.text.textFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun CommentBoxGray(
    value: State<String?>,
    modifier: Modifier,
    label: String,
    setValue: (String)->Unit
){

    val TAG = "CommentBoxGray"
    val comment = remember { mutableStateOf("")}
    LaunchedEffect(key1 = comment.value){
        setValue.invoke(comment.value)
    }
    TextField(
        value = comment.value,
        onValueChange = {
            comment.value = it
            setValue.invoke(it)
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
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        )
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
            label = "Enter optional stuff here",
            setValue = {}
        )
    }
}
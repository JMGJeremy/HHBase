package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun TfNames16(
    value: MutableState<String?>,
    label: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions? = null,
    trailingIcon: @Composable ()->Unit = {}
){

    val focus = LocalFocusManager.current

    TextField(
        value = value.value ?: "",
        singleLine = true,
        onValueChange = {
            value.value = it
        },
        modifier = modifier
            .height(60.dp)
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(50)
            ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            backgroundColor = Color.LightGray,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
            errorLabelColor = MaterialTheme.colorScheme.onPrimary,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary
        ),
        label = {
            Text(text = label,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        keyboardOptions = keyboardOptions,
        maxLines = 1,
        keyboardActions = keyboardActions
            ?: KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Next) }
            ),
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(50)
    )

}

@Preview
@Composable
fun TfNamePrev(){
    HHBaseTheme {
        TfNames16(
            value = remember { mutableStateOf("Text") },
            label = "Email Address",
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(),
            trailingIcon = {}
        )
    }
}
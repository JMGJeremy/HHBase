package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import com.jmg.baseproject.DroidFontFamily
import com.jmg.baseproject.HHBaseTheme

@Composable
fun TfEmail(
    value: MutableState<String?>,
    label: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    trailingIcon: @Composable ()->Unit = {}
){

    val focus = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {
        Text(text = label,
            modifier = Modifier
                .padding(bottom = 4.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
            )
        BasicTextField(
            value = value.value ?: "",
//            singleLine = true,
            onValueChange = {
                value.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 8.dp),
            textStyle = TextStyle(
                fontFamily = DroidFontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
//            colors = TextFieldDefaults.textFieldColors(
//                textColor = MaterialTheme.colorScheme.background,
//                focusedIndicatorColor = Color.Transparent,
//                errorIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                cursorColor = MaterialTheme.colorScheme.onPrimary,
//                backgroundColor = Color.LightGray,
//                disabledLabelColor = MaterialTheme.colorScheme.background,
//                errorLabelColor = MaterialTheme.colorScheme.background,
//                focusedLabelColor = MaterialTheme.colorScheme.background,
//                unfocusedLabelColor = MaterialTheme.colorScheme.background
//            ),
//            label = {
//                Text(
//                    text = label,
//                    style = TextStyle(
//                        color = Color.Gray,
//                        fontSize = 16.sp
//                    ),
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )
//            },
            keyboardOptions = keyboardOptions,
            maxLines = 1,
            keyboardActions = keyboardActions,
//            trailingIcon = trailingIcon,
//            shape = RoundedCornerShape(50)
        )
    }

}

@Preview
@Composable
fun LoginTextFieldPreview(){
    HHBaseTheme {
        TfEmail(
            value = remember { mutableStateOf("Text") },
            label = "Email Address",
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(),
            trailingIcon = {},
            keyboardActions = KeyboardActions()
        )
    }

}
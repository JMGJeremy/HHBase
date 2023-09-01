package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TfPass(
    input: MutableState<String?>,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    label: @Composable ()->Unit = {
        Text("Password",
            color = Color.Gray
        )
    },
    placeHolder: @Composable ()->Unit = {
        Text("Password",
            color = Color.Gray
        )
    },
    passwordVisible: MutableState<Boolean>,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.None,
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password
    ),
    focusDirection: FocusDirection,
    ){

    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    TextField(
        value = input.value ?: "",
        onValueChange = {
            input.value = it
        },
        modifier = modifier
            .height(60.dp),
        visualTransformation = if (passwordVisible.value){
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onBackground,
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
        label = label,
        placeholder = placeHolder,
        trailingIcon = {
            if (passwordVisible.value){
                FaIcon(faIcon = FaIcons.EyeSlash,
                    modifier = Modifier
                        .clickable {
                            passwordVisible.value = !passwordVisible.value
                        },
                    tint = Color.Gray
                )
            }else {
                FaIcon(faIcon = FaIcons.EyeRegular,
                    modifier = Modifier
                        .clickable {
                            passwordVisible.value = !passwordVisible.value
                        },
                    tint = Color.Gray
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(focusDirection)
                    keyboard?.hide()
                }
            ),
        shape = RoundedCornerShape(50),
        textStyle = TextStyle(
            fontSize = 18.sp
        ),
        maxLines = 1
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview(){
        TfPass(
            input = remember { mutableStateOf("password") },
            passwordVisible = remember {mutableStateOf(false) },
            focusDirection = FocusDirection.Down,
        )

}
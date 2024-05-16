package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.*
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
import com.jmg.baseproject.DroidFontFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TfPass(
    input: State<String?>,
    setInput: (String?)->Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
    label: String = "",
    passwordVisible: MutableState<Boolean>,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.None,
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password
    ),
    keyboardActions: KeyboardActions = KeyboardActions()
    ){

    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        BasicTextField(
            value = input.value ?: "",
            onValueChange = {
                setInput.invoke(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 8.dp),
            visualTransformation = if (passwordVisible.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            textStyle = TextStyle(
                fontFamily = DroidFontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun PasswordTextFieldPreview(){
        TfPass(
            input = remember { mutableStateOf("password") },
            passwordVisible = remember {mutableStateOf(false) },
            setInput = {}
        )

}
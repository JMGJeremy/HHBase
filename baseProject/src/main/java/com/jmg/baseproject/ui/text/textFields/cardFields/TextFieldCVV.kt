package com.jmg.baseproject.ui.text.textFields.cardFields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun TextFieldCVV(
    cvv: MutableState<String?>,
    modifier: Modifier
){
    TextField(
        value = cvv.value ?: "",
        onValueChange = {
            if (it.length <= 3) {
                cvv.value = it
            }
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
            errorContainerColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            errorIndicatorColor = MaterialTheme.colorScheme.onBackground,
            disabledIndicatorColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.background,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.background,
            errorTextColor = MaterialTheme.colorScheme.background,
            focusedSupportingTextColor = MaterialTheme.colorScheme.background,
            disabledTextColor = MaterialTheme.colorScheme.background,
            errorSupportingTextColor = MaterialTheme.colorScheme.background,
            disabledSupportingTextColor = MaterialTheme.colorScheme.background,
        ),
        shape = RectangleShape,
        label = {
            Text(text = "CVV",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.background,
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        )
    )
}

@Preview
@Composable
fun TextFieldCVVPrev(){
    HHBaseTheme {
        TextFieldCVV(cvv = remember { mutableStateOf("555") }, modifier = Modifier)
    }
}
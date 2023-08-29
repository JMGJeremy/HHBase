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
fun TextFieldZip(
    zip: MutableState<String?>,
    modifier: Modifier
){
    TextField(
        value = zip.value ?: "",
        onValueChange = {
            if (it.length <= 5) {
                zip.value = it
            }
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            errorContainerColor = Color.LightGray,
            disabledContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
            errorTextColor = MaterialTheme.colorScheme.onBackground,
            focusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = MaterialTheme.colorScheme.onBackground,
            errorSupportingTextColor = MaterialTheme.colorScheme.onBackground,
            disabledSupportingTextColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = RectangleShape,
        label = {
            Text(text = "Zip",
                style = TextStyle(
                    fontSize = 14.sp,
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
fun TextFieldZipPrev(){
    HHBaseTheme {
        TextFieldZip(zip = remember {
            mutableStateOf("51234")
        }, modifier = Modifier)
    }
}


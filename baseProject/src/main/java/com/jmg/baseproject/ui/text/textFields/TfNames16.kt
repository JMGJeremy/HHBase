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
import androidx.compose.runtime.*
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
    value: State<String?>,
    setValue:(String?)->Unit,
    label: String,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions? = null,
){

    val focus = LocalFocusManager.current
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(50)
            )
    ) {
        Text(text = label,
            modifier = Modifier
                .padding(bottom = 4.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
            )

        BasicTextField(
            value = value.value ?: "",
            singleLine = true,
            onValueChange = {
               setValue.invoke(it)
            },
            keyboardOptions = keyboardOptions,
            maxLines = 1,
            keyboardActions = keyboardActions
                ?: KeyboardActions(
                    onNext = { focus.moveFocus(FocusDirection.Next) }
                ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }

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
            setValue = {}
        )
    }
}
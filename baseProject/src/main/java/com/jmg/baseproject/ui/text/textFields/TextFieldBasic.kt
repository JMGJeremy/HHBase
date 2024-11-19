package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextFieldBasic(
    value: MutableState<String?>,
    setValue:(String?)->Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions? = null,
    modifier: Modifier,
    label: String = ""
) {
    val focus = LocalFocusManager.current

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
                onNext = { focus.moveFocus(FocusDirection.Down) },
            ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.value.isNullOrEmpty()) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                } else {
                    innerTextField()
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewTextFieldBasic() {
    TextFieldBasic(
        value = remember { mutableStateOf("") },
        setValue = {},
        keyboardOptions = KeyboardOptions.Default,
        modifier = Modifier,
        label = "Label"
    )
}
package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.visualTransformation.transform

@Composable
fun CurrencyTextField(
    value: MutableState<String?>
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(horizontal = 24.dp)
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = RoundedCornerShape(50)
            )
            .padding(vertical = 8.dp)
    ) {

        BasicTextField(
            value = value.value ?: "",
            onValueChange = {
                val split = it.split(".")
                if ((split.size == 2 && split[1].length <= 2) || split.size == 1) {
                    value.value = it
                }
            },
            modifier = Modifier
                .padding(start = 4.dp)
                .fillMaxSize(),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            visualTransformation = transform()
        )
    }
}

@Preview
@Composable
fun CurrnecyTextPrev(){
    HHBaseTheme {
        CurrencyTextField(value = remember {
            mutableStateOf("")
        })
    }
}
package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.DroidFontFamily
import com.jmg.baseproject.R

@Composable
fun TfPass(
    input: State<String?>,
    setInput: (String?)->Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.None,
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password
    ),
    keyboardActions: KeyboardActions = KeyboardActions()
    ){

    var vis by remember { mutableStateOf(true)}

    Column (
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = 4.dp),
        )

        Box(

        ) {
            BasicTextField(
                value = input.value ?: "",
                onValueChange = {
                    setInput.invoke(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 8.dp)
                    .height(46.dp),
                visualTransformation = if (vis) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                textStyle = TextStyle(
                    fontFamily = DroidFontFamily,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                maxLines = 1,
                decorationBox = { box ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        box()
                    }
                }
            )

            Image(
                painter = painterResource(
                    id = if (vis) {
                        R.drawable.eye
                    } else {
                        R.drawable.eye_slash
                    }
                ),
                contentDescription = "Password visibility",
                modifier = Modifier
                    .clickable {
                        vis = !vis
                    }
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterEnd),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimaryContainer),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview
@Composable
fun PasswordTextFieldPreview(){
        TfPass(
            input = remember { mutableStateOf("password") },
            setInput = {}
        )

}
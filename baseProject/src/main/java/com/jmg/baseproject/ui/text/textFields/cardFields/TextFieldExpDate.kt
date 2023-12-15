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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun TextFieldExpDate(
    date: MutableState<String?>,
    modifier: Modifier
){
    fun formatExpDate(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 6) text.text.substring(0..5) else text.text
        var out = ""

        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i == 1) out += "/"
        }
        val creditCardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 6) return offset + 1
                return 7
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 6) return offset - 1
                return 6
            }
        }

        return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
    }
    TextField(
        value = date.value ?: "",
        onValueChange = {
            if (it.length <= 4) {
                date.value = it
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
        visualTransformation = {
            formatExpDate(text = it)
                               },
        label = {
            Text(text = "Exp. Date",
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


object ExpirationVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformed = StringBuilder().apply {
            text.text.filter { it.isDigit() }.forEachIndexed { index, c ->
                if (index != 0 && index % 2 == 0) {
                    append('/')
                }
                append(c)
            }
        }
        return TransformedText(
            AnnotatedString(transformed.toString()),
            ExpirationOffsetMapping(text.text)
        )
    }
}

class ExpirationOffsetMapping(original: String) : OffsetMapping {
    private val originalToTransformed = calculateMapping(original)

    private fun calculateMapping(original: String): IntArray {
        val mapping = IntArray(original.length + 1)
        var transformationOffset = 0
        original.forEachIndexed { index, char ->
            if (char.isDigit() && index >= 2) {
                transformationOffset++
            }
            mapping[index] = index + transformationOffset
        }
        mapping[original.length] = original.length + transformationOffset
        return mapping
    }

    override fun originalToTransformed(offset: Int): Int {
        return originalToTransformed.getOrElse(offset) { originalToTransformed.last() }
    }

    override fun transformedToOriginal(offset: Int): Int {
        val originalOffset = originalToTransformed.indexOf(offset)
        return if (originalOffset != -1) originalOffset else offset
    }
}

@Preview
@Composable
fun TextFieldExpDatePrev(){
    HHBaseTheme {
        TextFieldExpDate(
            date = remember { mutableStateOf("0524") },
            modifier = Modifier
            )
    }
}
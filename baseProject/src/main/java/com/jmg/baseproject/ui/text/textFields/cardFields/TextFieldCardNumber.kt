package com.jmg.baseproject.ui.text.textFields.cardFields

import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun TextFieldCardNumber(
    cardNumber: MutableState<String?>,
    modifier: Modifier
){

    TextField(
        value = cardNumber.value ?: "",
        onValueChange = {
            if (it.length <= 16) {
                cardNumber.value = it
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
            unfocusedTextColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.background,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.background,
            errorTextColor = MaterialTheme.colorScheme.background,
            focusedSupportingTextColor = MaterialTheme.colorScheme.background,
            disabledTextColor = MaterialTheme.colorScheme.background,
            errorSupportingTextColor = MaterialTheme.colorScheme.background,
            disabledSupportingTextColor = MaterialTheme.colorScheme.background,
        ),
        shape = androidx.compose.ui.graphics.RectangleShape,
        visualTransformation = CreditCardVisualTransformation,
        label = {
            Text(text = "Credit Card Number",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.background
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        )
    )

}

object CreditCardVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformed = StringBuilder().apply {
            text.text.filter { it.isDigit() }.forEachIndexed { index, c ->
                if (index != 0 && index % 4 == 0) {
                    append(' ')
                }
                append(c)
            }
        }
        return TransformedText(
            AnnotatedString(transformed.toString()),
            CreditCardOffsetMapping(text.text)
        )
    }
}

class CreditCardOffsetMapping(original: String) : OffsetMapping {
    private val originalToTransformed = calculateMapping(original)

    private fun calculateMapping(original: String): IntArray {
        val mapping = IntArray(original.length + 1)
        var transformationOffset = 0
        original.forEachIndexed { index, char ->
            if (char.isDigit() && index % 4 == 0 && index != 0) {
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
fun TextFieldCardNumberPrev(){
    HHBaseTheme {
        TextFieldCardNumber(
            cardNumber = remember { mutableStateOf("5555555555555555") },
            modifier = Modifier
        )
    }
}


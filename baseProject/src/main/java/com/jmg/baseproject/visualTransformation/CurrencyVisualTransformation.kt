package com.jmg.baseproject.visualTransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun transform(): VisualTransformation {
    return VisualTransformation { text ->
        val prefix = "$ "
        // Filter the input to include only digits and decimal point
        val filteredText = text.filter { it.isDigit() || it == '.' }

        // Convert the filtered text to a number and then format it as currency
        val number = filteredText.toString().toDoubleOrNull() ?: 0.0
//        val formattedNumber = NumberFormat.getNumberInstance(Locale.US).format(number)
        val decimalFormat = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.US))
        val formattedNumber = decimalFormat.format(number)
        val out = prefix + formattedNumber

        val mapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return prefix.length
                // Estimate the transformed offset, may need adjustment for specific locales/formats
                return (prefix.length + formattedNumber.length - (filteredText.length - offset)).coerceAtMost(out.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= prefix.length) return 0
                // Estimate the original offset, may need adjustment for specific locales/formats
                return (offset - prefix.length + (filteredText.length - formattedNumber.length)).coerceAtLeast(0)
            }
        }

        TransformedText(AnnotatedString(out), mapping)
    }
}

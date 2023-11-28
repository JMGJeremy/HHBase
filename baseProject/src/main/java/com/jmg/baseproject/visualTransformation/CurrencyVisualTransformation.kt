package com.jmg.baseproject.visualTransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

fun transform():VisualTransformation{
    val trans = VisualTransformation { text ->
        val prefix = "$ "
        val filteredText = text.filter { it.isDigit() || it == '.' }
        val out = prefix + filteredText

        val mapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Add the length of the prefix to the offset, but don't exceed the length of the transformed text
                return (prefix.length + offset).coerceAtMost(out.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Subtract the length of the prefix from the offset, but don't go below zero
                return (offset - prefix.length).coerceAtLeast(0)
            }
        }

        TransformedText(AnnotatedString(out), mapping)
    }
    return trans
}

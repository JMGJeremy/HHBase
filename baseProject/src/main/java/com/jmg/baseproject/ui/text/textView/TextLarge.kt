package com.jmg.baseproject.ui.text.textView

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme

@Composable
fun TextLarge(
    text: String,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: Int = 24,
    modifier: Modifier = Modifier,
    weight: Int = 400
) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        fontSize = fontSize.sp,
        fontWeight = FontWeight(weight),
        modifier = modifier
    )
}

@Preview
@Composable
fun TextLargePreview(){
    HHBaseTheme {
        TextLarge(
            text = "Staffing is Hard - Parrot.MD Makes It Easy."
        )
    }
}
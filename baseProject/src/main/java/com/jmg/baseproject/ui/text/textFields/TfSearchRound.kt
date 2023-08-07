package com.jmg.baseproject.ui.text.textFields

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R

@Composable
fun TfSearchRound(
    drawable: Int,
    value: MutableState<String>,
    label: String,
    modifier: Modifier,
    search: (String)->Unit
){

    TextField(
        value = value.value,
        onValueChange = {v->
            value.value = v
                        },
        label = {
            Text(
                text = label,
                color = Color.Black
            )
        },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier,
        leadingIcon = {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable{
                        search.invoke(value.value)
                    },
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

    )

}

@Preview
@Composable
fun TfSearchRoundPrev(){
    HHBaseTheme {
        TfSearchRound(
            drawable = R.drawable.search,
            value = remember { mutableStateOf("")},
            label = "Search",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            search = {}
        )
    }
}
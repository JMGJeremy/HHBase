package com.jmg.baseproject.ui.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.R

@Composable
fun TopBarTextBack(
    text: String,
    onBack: ()->Unit,
    modifier: Modifier
){
    Box(
        modifier = modifier
    ){
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
            )

            Text(
                text = "Back",
                modifier = Modifier
                    .clickable {
                        onBack.invoke()
                    }
            )
        }
    }
}
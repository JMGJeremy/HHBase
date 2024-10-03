package com.jmg.baseproject.ui.rowColumn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.R

@Composable
fun SwipeScreens(
    screens: List<@Composable ()->Unit>,
    currentScreen: State<Int>,
    backIcon: Int = R.drawable.xmark,
    back: () -> Unit,
    changeScreen: (Int)->Unit
){

    val dragState = rememberDraggableState { delta -> }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .draggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                onDragStopped = { v->
                    if (v > 0 && currentScreen.value > 0){
                        changeScreen.invoke(-1)
                    }else if (v < 0 && currentScreen.value < screens.size - 1){
                        changeScreen.invoke(1)
                    }
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = backIcon),
                contentDescription = "Close Button",
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        back.invoke()
                    },
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
            )
        }

        screens[currentScreen.value].invoke()

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(10.dp)
                        .background(
                            color = if (it == screens[currentScreen.value]) MaterialTheme.colorScheme.primary else Color.Gray,
                            shape = MaterialTheme.shapes.small
                        )
                ) {

                }
            }
        }
    }
}
package com.jmg.baseproject.ui.selectors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDialog(
    time: MutableState<String>,
    timePickerState: TimePickerState,
    hideTimePicker: ()->Unit,
    format: String
) {
    val tFormat = remember { SimpleDateFormat(format, Locale.getDefault()) }

    LaunchedEffect(key1 = timePickerState) { }
    Dialog(
        onDismissRequest = {hideTimePicker.invoke()},
        content = {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(6))
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(
                    state = timePickerState,
                    layoutType = TimePickerLayoutType.Vertical,
                    colors = TimePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.background,
                        clockDialColor = MaterialTheme.colorScheme.background,
                        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.onBackground,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.background,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.background,
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.onSurface,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.onSurface,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onBackground,
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.onBackground,
                        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onBackground,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onBackground,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onBackground,
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "OK",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                val cal = Calendar.getInstance()
                                cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                cal.set(Calendar.MINUTE, timePickerState.minute)
                                cal.isLenient = false

                                time.value = tFormat.format(cal.timeInMillis)
                                hideTimePicker.invoke()
                            },
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TimeDialogPreview() {
    TimeDialog(
        time = remember { mutableStateOf("12:00")},
        timePickerState = rememberTimePickerState(
            initialMinute = Date().minutes,
            initialHour = Date().hours,
            is24Hour = false,
        ),
        hideTimePicker = {},
        format = "hh:mm a"
    )
}
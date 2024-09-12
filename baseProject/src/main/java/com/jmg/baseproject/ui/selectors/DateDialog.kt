package com.jmg.baseproject.ui.selectors

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    datePickerState: DatePickerState,
    hideDatePicker: ()->Unit,
    date: MutableState<String>,
    format: String
){
    val formatter = remember { SimpleDateFormat(format, Locale.US) }
    formatter.timeZone = TimeZone.getTimeZone("UTC")

    fun setDate(){
        date.value = formatter.format(datePickerState.selectedDateMillis)
    }

    LaunchedEffect(key1 = datePickerState.selectedDateMillis) {
        setDate()
    }

    DatePickerDialog(
        onDismissRequest = {
            hideDatePicker.invoke()
        },
        confirmButton = {
            Text(text = "OK",
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .clickable {
                        setDate()
                        hideDatePicker.invoke()
                    }
            )
                        },
        dismissButton = {
//                showDatePicker = false
        }
    ){
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background,
                dateTextFieldColors = TextFieldDefaults.colors(
                    unfocusedTextColor = MaterialTheme.colorScheme.background,
                    focusedTextColor = MaterialTheme.colorScheme.background,
                    errorTextColor = MaterialTheme.colorScheme.background,
                    disabledTextColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.onBackground,
                    errorContainerColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    errorLabelColor = MaterialTheme.colorScheme.onBackground,
                    disabledLabelColor = MaterialTheme.colorScheme.background
                ),
                dayContentColor = MaterialTheme.colorScheme.onBackground,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                todayContentColor = MaterialTheme.colorScheme.onBackground,
                weekdayContentColor = MaterialTheme.colorScheme.onBackground,
                selectedDayContentColor = MaterialTheme.colorScheme.onBackground,
                selectedYearContentColor = MaterialTheme.colorScheme.onBackground,
                currentYearContentColor = MaterialTheme.colorScheme.onBackground,
                headlineContentColor = MaterialTheme.colorScheme.onBackground,
                subheadContentColor = MaterialTheme.colorScheme.onBackground,
                disabledSelectedDayContentColor = MaterialTheme.colorScheme.onBackground,
                disabledDayContentColor = MaterialTheme.colorScheme.onBackground,
                dayInSelectionRangeContentColor = MaterialTheme.colorScheme.primary,
                disabledYearContentColor = MaterialTheme.colorScheme.onBackground,
                navigationContentColor = MaterialTheme.colorScheme.onBackground,
                yearContentColor = MaterialTheme.colorScheme.onBackground,
                disabledSelectedYearContentColor = MaterialTheme.colorScheme.onBackground,
            ),
            showModeToggle = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DateDialogPreview(){
    DateDialog(
        datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = Instant.now().toEpochMilli(),
            initialDisplayMode = DisplayMode.Picker,
            selectableDates = object : SelectableDates{
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= System.currentTimeMillis()
                }
            }
        ),
        hideDatePicker = { },
        date = remember { mutableStateOf("") },
        format = "dd/MM/yyyy"
    )
}
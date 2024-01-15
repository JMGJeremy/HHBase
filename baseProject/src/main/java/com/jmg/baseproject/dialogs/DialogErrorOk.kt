package com.jmg.baseproject.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorDialogOk(
    click: ()->Unit,
    error: String?
){

    AlertDialog(
        onDismissRequest = { click.invoke() },
        title = { Text(
            text = "Error",
            color = MaterialTheme.colorScheme.background
        ) },
        text = { Text(
            text = error ?: "Whoops",
            color = MaterialTheme.colorScheme.background
        ) },
        confirmButton = {
            Text(text = "Ok",
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .clickable { click.invoke() }
                )
        },
        containerColor = MaterialTheme.colorScheme.onBackground
    )

}
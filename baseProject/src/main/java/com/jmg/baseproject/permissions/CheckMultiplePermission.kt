package com.jmg.baseproject.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckMultiplePermission(
    string: List<String>
){
    val launcher = rememberMultiplePermissionsState(string)
    LaunchedEffect(Unit) {
        launcher.launchMultiplePermissionRequest()
    }
}
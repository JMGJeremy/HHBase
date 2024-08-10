package com.jmg.baseproject.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermission(
    string: String
){
    val launcher = rememberPermissionState(string)
    LaunchedEffect(Unit) {
        launcher.launchPermissionRequest()
    }
}
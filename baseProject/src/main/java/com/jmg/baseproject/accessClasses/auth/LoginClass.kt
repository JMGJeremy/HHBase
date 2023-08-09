package com.jmg.baseproject.accessClasses.auth

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.jmg.baseproject.ui.auth.BaseLoginViewModel
import com.jmg.baseproject.ui.auth.LoginScreen
import retrofit2.Response

class LoginClass(
    baseUrl: String,
    register: ()->Unit
) {

    private val viewModel = BaseLoginViewModel(baseUrl = baseUrl)
    private var reg = register

    @Composable
    fun GetLoginScreen(
        email: MutableState<String?>,
        password: MutableState<String?>,
        passwordVis: MutableState<Boolean>,
        forgot: ()->Unit,
        error: MutableState<String?>,
        logo: Int,
        response: MutableState<Response<Any?>?>,
        progress: MutableState<Boolean>
        ){

        LoginScreen(
            email = email,
            password = password,
            passwordVis = passwordVis,
            forgot = { forgot.invoke() },
            login = {
                viewModel.loginUser(
                    email = email.value ?: "",
                    password = password.value ?: "",
                    error = error,
                    response = response
                )
            },
            error = error,
            logo = logo,
            register = reg,
            progress = progress
        )
    }
}
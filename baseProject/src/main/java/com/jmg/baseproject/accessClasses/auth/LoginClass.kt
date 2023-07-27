package com.jmg.baseproject.accessClasses.auth

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import com.jmg.baseproject.ui.auth.BaseLoginViewModel
import com.jmg.baseproject.ui.auth.LoginScreen
import retrofit2.Response

class LoginClass(baseUrl: String) {

    private val viewModel = BaseLoginViewModel(baseUrl = baseUrl)

    @Composable
    fun GetLoginScreen(
        email: MutableState<String?>,
        password: MutableState<String?>,
        passwordVis: MutableState<Boolean>,
        forgot: ()->Unit,
        error: MutableState<String?>,
        logo: Int,
        response: MutableState<Response<Any?>?>
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
        )
    }
}
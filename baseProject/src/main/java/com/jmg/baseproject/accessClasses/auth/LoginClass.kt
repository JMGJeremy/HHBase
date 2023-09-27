package com.jmg.baseproject.accessClasses.auth

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.jmg.baseproject.models.auth.LoginResponse
import com.jmg.baseproject.ui.auth.BaseLoginViewModel
import com.jmg.baseproject.ui.auth.LoginScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
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
        response: MutableState<LoginResponse?>,
        progress: MutableState<Boolean>
        ){

        LoginScreen(
            email = email,
            password = password,
            passwordVis = passwordVis,
            forgot = { forgot.invoke() },
            login = {
                try {
                    CoroutineScope(Dispatchers.IO).launch {
                        val resp = viewModel.loginUser(
                            email = email.value ?: "",
                            password = password.value ?: "",
                        )
                        if (resp.isSuccessful && resp.body() != null) {
                            response.value = resp.body()
                        }else if(resp.code() > 299){
                            error.value = resp.message()
                        }
                        progress.value = false
                    }
                }catch (e: HttpException){
                    progress.value = false
                    error.value = e.localizedMessage
                }catch(e: Exception){
                    progress.value = false
                    error.value = e.localizedMessage
                }
            },
            error = error,
            logo = logo,
            register = reg,
            progress = progress
        )
    }
}
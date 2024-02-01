package com.jmg.baseproject.accessClasses.auth

import android.widget.Toast
import androidx.compose.runtime.*
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
        error: State<String?>,
        logo: Int,
        response: MutableState<LoginResponse?>,
        setProgress: (Boolean)->Unit,
        setEmail: (String?)->Unit,
        setPassword: (String?)->Unit,
        setError: (String?)->Unit
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
                            setError.invoke(resp.message())
                        }
                        setProgress.invoke(false)
                    }
                }catch (e: HttpException){
                    setProgress.invoke(false)
                    setError.invoke(e.localizedMessage)
                }catch(e: Exception){
                    setProgress.invoke(false)
                    setError.invoke(e.localizedMessage)
                }
            },
            setError = { setError.invoke(it) },
            logo = logo,
            register = reg,
            setProgress = {
                setProgress.invoke(true)
            },
            setEmail = {setEmail.invoke(it)},
            setPassword = {setPassword.invoke(it)}
        )
    }
}
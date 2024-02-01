package com.jmg.baseproject.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.buttons.B18PrimaryBodyMedRound
import com.jmg.baseproject.ui.images.Logo
import com.jmg.baseproject.ui.text.textFields.TfEmail
import com.jmg.baseproject.ui.text.textFields.TfPass

@Composable
fun LoginScreen(
    email: State<String?>,
    password: State<String?>,
    passwordVis: MutableState<Boolean>,
    forgot: ()-> Unit,
    login: () -> Unit,
    setError: (String?) ->Unit,
    logo: Int,
    register: ()-> Unit,
    setProgress: (Boolean)->Unit,
    setPassword: (String?)->Unit,
    setEmail: (String?)->Unit
){

    val context = LocalContext.current
    val scroll = rememberScrollState()
    val focus = LocalFocusManager.current

    fun loginUser(){
        when(true){
            email.value.isNullOrEmpty() -> {setError.invoke("Please enter your email")}
            !(email.value?.contains("@") == true && email.value?.contains(".") == true)-> {setError.invoke("Email is malformed")}
            password.value.isNullOrEmpty() -> { setError.invoke("Please enter your password")}
            else -> {
                setProgress.invoke(true)
                login.invoke()
            }
        }
    }

    Column(
    modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .padding(horizontal = 36.dp)
        .verticalScroll(scroll),
    horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Logo(
            context = context,
            logo = logo,
            modifier = Modifier
                .height(300.dp)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            TfEmail(
                value = email,
                label = "Email Address",
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                ),
                setValue = {setEmail.invoke(it)}
            )

            TfPass(
                input = password,
                passwordVisible = passwordVis,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                    ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        loginUser()
                    }
                ),
                label = "Password",
                setInput = {setPassword.invoke(it)}
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ){

            B18PrimaryBodyMedRound(
                click = {
                    loginUser()
                },
                text = "Login",
                textStyle = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Forgot password?",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable {
                            forgot.invoke()
                        }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? Sign up",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable {
                            register.invoke()
                        }
                )
            }
        }
    }

}

@Preview
@Composable
fun LoginViewPreview(){
    LoginScreen(
        email = remember { mutableStateOf("") },
        password = remember { mutableStateOf("") },
        passwordVis = remember { mutableStateOf(false) },
        forgot = {},
        login = {},
        setError = {},
        logo = R.drawable.logo,
        register = {},
        setProgress = {

        },
        setPassword = {},
        setEmail = {}
    )

}
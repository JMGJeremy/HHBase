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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
    email: MutableState<String?>,
    password: MutableState<String?>,
    passwordVis: MutableState<Boolean>,
    forgot: ()-> Unit,
    login: () -> Unit,
    error: MutableState<String?>,
    logo: Int,
    register: ()-> Unit,
    progress: MutableState<Boolean>
){

    val context = LocalContext.current
    val scroll = rememberScrollState()

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
        ) {

            TfEmail(
                value = email,
                label = "Email Address",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                focusDirection = FocusDirection.Next
            )

            TfPass(
                input = password,
                passwordVisible = passwordVis,
                textColor = Color.Gray,
                focusDirection = FocusDirection.Next,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    ),
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
                    when(true){
                        email.value.isNullOrEmpty() -> {error.value = "Please enter your email"}
                        !(email.value?.contains("@") == true && email.value?.contains(".") == true)-> {error.value = "Email is malformed"}
                        password.value.isNullOrEmpty() -> { error.value = "Please enter your password"}
                        else -> {
                            progress.value = true
                            login.invoke()
                        }
                    }
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
        error = remember { mutableStateOf(null) },
        logo = R.drawable.logo,
        register = {},
        progress = remember { mutableStateOf(false)}
    )

}
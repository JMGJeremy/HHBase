package com.jmg.hhbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.accessClasses.auth.*
import com.jmg.baseproject.ui.auth.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HHBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    LoginClass(
//                        baseUrl = "https://sandbox.homeworkhelperapp.org",
//                        register = {}
//                    ).GetLoginScreen(
//                        email = remember { mutableStateOf("") },
//                        password = remember { mutableStateOf("") },
//                        passwordVis = remember { mutableStateOf(false) },
//                        forgot = { /*TODO*/ },
////                        login = { /*TODO*/ },
//                        error = remember { mutableStateOf("") },
//                        logo = R.drawable.ic_launcher_foreground,
//                        response = remember { mutableStateOf(null) },
//                        progress = remember {
//                            mutableStateOf(false)
//                        }
//                    )

                    RegisterClass(
                        baseUrl = "https://sandbox.homeworkhelperapp.org",
                    ).GetRegisterScreen(
                        resp = remember { mutableStateOf(null)},
                        err = remember { mutableStateOf(null)},
                        first = remember { mutableStateOf("") },
                        last = remember { mutableStateOf("") },
                        email = remember { mutableStateOf("") },
                        pass = remember { mutableStateOf("") },
                        confirm = remember { mutableStateOf("") },
                        logoInt = R.drawable.ic_launcher_foreground,
                        back = {},
                        optionOne = "Parent",
                        optionTwo = "Student",
                        terms = {},
                        progress = remember { mutableStateOf(false)},
                        zip = remember { mutableStateOf(null)}
                    )
//                }

//                    PaymentScreen(
//                        addCard = {},
//                        skippable = true,
//                        skip = {
//
//                        },
//                        error = remember { mutableStateOf(null) }
//                    )

//                    VideoScreen(
//                        facingFront = true,
//                        onImageCaptured = {uri, b->},
//                        maxTime = 10000L
//                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HHBaseTheme {
        RegisterScreen(
            firstName = mutableStateOf("Jeremy"),
            lastName = mutableStateOf("Kruid"),
            email = mutableStateOf("jeremy@vconn.org"),
            password = mutableStateOf(""),
            confirm = mutableStateOf(""),
            zip = mutableStateOf("51234"),
            register = { /*TODO*/ },
            errorText = mutableStateOf("error"),
            back = { /*TODO*/ },
            logo = R.mipmap.ic_launcher,
            termsClick = { /*TODO*/ },
            option1 = "Parent",
            option2 = "Student",
            selected = mutableStateOf("Parent"),
            progress = mutableStateOf(false)
        )
    }
}
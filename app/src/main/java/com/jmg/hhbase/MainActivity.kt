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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.jmg.baseproject.accessClasses.auth.*
import com.jmg.baseproject.ui.auth.LoginScreen
import com.jmg.hhbase.ui.theme.HHBaseTheme
import com.jmg.baseproject.dialogs.ProgressDialogText
import com.jmg.baseproject.ui.auth.RegisterScreen
import retrofit2.Response

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
//                    LoginClass(baseUrl = "https://sandbox.homeworkhelperapp.org").GetLoginScreen(
//                        email = remember { mutableStateOf("") },
//                        password = remember { mutableStateOf("") },
//                        passwordVis = remember { mutableStateOf(false) },
//                        forgot = { /*TODO*/ },
////                        login = { /*TODO*/ },
//                        error = remember { mutableStateOf("") },
//                        logo = R.drawable.ic_launcher_foreground,
//                        response = remember { mutableStateOf(null) }
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
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HHBaseTheme {
        LoginScreen(
            email = remember { mutableStateOf("text") },
            password = remember { mutableStateOf("pass") },
            passwordVis = remember { mutableStateOf(false) },
            forgot = {},
            login = {},
            error = remember { mutableStateOf(null)},
            logo = R.drawable.ic_launcher_foreground
        )
    }
}
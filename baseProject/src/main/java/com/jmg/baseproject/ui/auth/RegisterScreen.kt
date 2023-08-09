package com.jmg.baseproject.ui.auth

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.images.Logo
import com.jmg.baseproject.ui.buttons.B18PrimaryBodyMedRound
import com.jmg.baseproject.ui.text.textFields.TfEmail
import com.jmg.baseproject.ui.text.textFields.TfNames16
import com.jmg.baseproject.ui.text.textFields.TfPass
import com.jmg.baseproject.ui.topBar.TopBarDrawableText


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    firstName: MutableState<String?>,
    lastName: MutableState<String?>,
    email: MutableState<String?>,
    password: MutableState<String?>,
    confirm: MutableState<String?>,
    register: () -> Unit,
    errorText: MutableState<String?>,
    back: ()->Unit,
    logo: Int,
    termsClick: ()->Unit,
    option1: String,
    option2: String,
    selected: MutableState<String>,
    progress: MutableState<Boolean>
){

    val context = LocalContext.current
    val terms = remember { mutableStateOf(false) }
    val progress = remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current

    val passwordVis = remember { mutableStateOf<Boolean>(true)}

    val mod = Modifier
        .fillMaxWidth()
        .padding(top = 14.dp)

    val scrollState = rememberScrollState()

    val enabled = !(
            firstName.value.isNullOrEmpty() &&
                    lastName.value.isNullOrEmpty() &&
                    email.value.isNullOrEmpty() &&
                    password.value.isNullOrEmpty() &&
                    confirm.value.isNullOrEmpty()
            )

    val termsString = buildAnnotatedString {
        addStringAnnotation(tag = "text", annotation = "", start = 0, end = 5)
        withStyle(style = SpanStyle(
            color = MaterialTheme.colorScheme.primary
        )
        ){
            append("By clicking the create account button, I agree to the ")
        }
        addStringAnnotation("Terms of Service", annotation = "", start = 0, end = 52)
        withStyle(style = SpanStyle(
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.primary,
        )
        ){
            append("Terms of Service")
        }
    }

    BackHandler {
        back.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){

        TopBarDrawableText(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(50.dp),
            drawable = R.drawable.xmark,
            text = "Sign Up"
        ) {
            back.invoke()
        }


        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(36.dp)
                .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
                .padding(4.dp)
        ){
            Row(
                modifier = Modifier
                    .padding(end = 2.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .background(
                        color = if (selected.value == option1){
                            MaterialTheme.colorScheme.background
                        } else{
                              Color.Transparent
                              },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { selected.value = option1 },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = option1,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                )
            }


            Row(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .background(
                        color = if (selected.value == option2){
                            MaterialTheme.colorScheme.background
                        } else{
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { selected.value = option2 },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = option2,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                )
            }
        }

//        Logo(
//            context = context,
//            logo = logo,
//            modifier = Modifier
//                .height(200.dp),
//        )

//        MasterHaulerLogoSmall()

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            TfNames16(
                value = firstName,
                label = "First Name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                modifier = mod
            )



            TfNames16(
                value = lastName,
                label = "Last Name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                modifier = mod
            )

            TfEmail(
                value = email,
                label = "Email",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = mod,
                focusDirection = FocusDirection.Down
            )

            TfPass(
                input = password,
                passwordVisible = passwordVis,
                textColor = MaterialTheme.colorScheme.onPrimary,
                focusDirection = FocusDirection.Down,
            )


            TfPass(
                input = confirm,
                passwordVisible = passwordVis,
                textColor = MaterialTheme.colorScheme.onPrimary,
                focusDirection = FocusDirection.Down,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column() {
            B18PrimaryBodyMedRound(
                click = {
                    when (true) {
                        firstName.value.isNullOrEmpty() -> {
                            errorText.value = "Please enter your first name."
                        }

                        lastName.value.isNullOrEmpty() -> {
                            errorText.value = "Please enter your last name."
                        }

                        email.value.isNullOrEmpty() -> {
                            errorText.value = "Please enter your email."
                        }

                        password.value.isNullOrEmpty() -> {
                            errorText.value = "Please enter your password."
                        }

                        confirm.value.isNullOrEmpty() -> {
                            errorText.value = "Please enter your password confirmation."
                        }

//                        !terms.value -> {
//                            errorText.value =
//                                "Please review our Terms and Conditions and accept before continuing."
//                        }

                        else -> {
                            progress.value = true
                            register.invoke()
                        }
                    }
                },
                text = "Submit",
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.background
                ),
                enabled = enabled,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 8.dp)
            )

            ClickableText(
                text = termsString,
                onClick = {
                    progress.value = true
                          termsClick.invoke()
                },
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview
@Composable
fun RegisterViewPreview(){
    HHBaseTheme {
        RegisterScreen(
            firstName =  remember { mutableStateOf("") },
            lastName = remember { mutableStateOf("") },
            email = remember { mutableStateOf("") },
            password = remember { mutableStateOf("") },
            confirm = remember { mutableStateOf("") },
            register = {},
            errorText = remember { mutableStateOf(null) },
            back = {},
            logo = R.drawable.logo,
            termsClick = {},
            option1 = "Parent",
            option2 = "Student",
            selected = remember {mutableStateOf("Parent")},
            progress = remember { mutableStateOf(false)}
        )
    }
}

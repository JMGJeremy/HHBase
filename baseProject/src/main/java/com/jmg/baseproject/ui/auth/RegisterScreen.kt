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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.sp
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
    zip: MutableState<String?>,
    register: () -> Unit,
    setError: (String?)->Unit,
    back: ()->Unit,
    termsClick: ()->Unit,
    option1: String,
    option2: String,
    selected: MutableState<String>,
    setProgress: (Boolean)->Unit,
    setEmail:  (String?) ->Unit,
    setName:  (String?) ->Unit,
    setLastName:  (String?) ->Unit,
    setZip:  (String?) ->Unit,
    setInput: (String?) ->Unit
){

    val passwordVis = remember { mutableStateOf<Boolean>(true)}

    fun registerUser(){
        when (true) {
            firstName.value.isNullOrEmpty() -> {
                setError.invoke("Please enter your first name.")
            }

            lastName.value.isNullOrEmpty() -> {
                setError.invoke("Please enter your last name.")
            }

            email.value.isNullOrEmpty() -> {
                setError.invoke("Please enter your email.")
            }

            password.value.isNullOrEmpty() -> {
                setError.invoke("Please enter your password.")
            }

//            confirm.value.isNullOrEmpty() -> {
//                errorText.value = "Please enter your password confirmation."
//            }
            else -> {
                setProgress.invoke(true)
                register.invoke()
            }
        }
    }

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
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp
        )
        ){
            append("By clicking the create account button, I agree to the ")
        }
        addStringAnnotation("Terms of Service", annotation = "", start = 0, end = 52)
        withStyle(style = SpanStyle(
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp
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
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp)
                )
        ){
            Row(
                modifier = Modifier
                    .padding(end = 2.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .background(
                        color = if (selected.value == option1) {
                            MaterialTheme.colorScheme.surfaceTint
                        } else {
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
                        color = if (selected.value == option2) {
                            MaterialTheme.colorScheme.surfaceTint
                        } else {
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
                modifier = mod,
                setValue = {setName.invoke(it)}
            )



            TfNames16(
                value = lastName,
                label = "Last Name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                modifier = mod,
                setValue = {setLastName.invoke(it)}
            )

            TfNames16(
                value = zip,
                label = "Zip Code",
                modifier = mod,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                setValue = {setZip.invoke(it)}
            )

            TfNames16(
                value = email,
                label = "Email",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = mod,
                setValue = {setEmail.invoke(it)}
            )

            TfPass(
                input = password,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        registerUser()
                    }
                ),
                label = "Password",
                setInput = {setInput.invoke(it)}
            )


//            TfPass(
//                input = confirm,
//                passwordVisible = passwordVis,
//                textColor = MaterialTheme.colorScheme.onPrimary,
//                keyboardOptions = KeyboardOptions(
//                    imeAction = ImeAction.Done
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        registerUser()
//                    }
//                )
//            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column() {
            B18PrimaryBodyMedRound(
                click = {
                    registerUser()
                },
                text = "Create Account",
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
                    setProgress.invoke(true)
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
            firstName =  remember { mutableStateOf("sdf") },
            lastName = remember { mutableStateOf("sdf") },
            email = remember { mutableStateOf("sdf") },
            password = remember { mutableStateOf("dsf") },
            confirm = remember { mutableStateOf("sdf") },
            register = {},
            setError = {},
            back = {},
            termsClick = {},
            option1 = "Parent",
            option2 = "Student",
            selected = remember {mutableStateOf("Student")},
            zip = remember { mutableStateOf("")},
            setProgress = {},
            setEmail=  {},
        setName= {},
        setLastName=  {},
        setZip=  {},
        setInput= {}
        )
    }
}

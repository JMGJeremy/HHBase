package com.jmg.baseproject.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.jmg.baseproject.HHBaseTheme
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.buttons.B18PrimaryBodyMedRound
import com.jmg.baseproject.ui.text.textFields.TfEmail
import com.jmg.baseproject.ui.text.textFields.TfNames16
import com.jmg.baseproject.ui.text.textFields.TfPass


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    firstName: MutableState<String?>,
    lastName: MutableState<String?>,
    email: MutableState<String?>,
    password: MutableState<String?>,
    confirm: MutableState<String?>,
    register: () -> Unit,
    navBack: ()->Unit,
    errorText: MutableState<String?>,

){

    val context = LocalContext.current
    val terms = remember { mutableStateOf(false) }
    val progress = remember { mutableStateOf(false) }
    val keyboard = LocalSoftwareKeyboardController.current

    val passwordVis = remember { mutableStateOf<Boolean>(false)}

    val mod = Modifier
        .fillMaxWidth()
        .padding(top = 14.dp)

    val scrollState = rememberScrollState()

    val enabled = !(
            firstName.value.isNullOrEmpty() &&
                    lastName.value.isNullOrEmpty() &&
                    email.value.isNullOrEmpty() &&
                    password.value.isNullOrEmpty() &&
                    confirm.value.isNullOrEmpty() &&
                    !terms.value
            )

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        ConstraintLayout(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ){
            val (back, text) = createRefs()

            FaIcon(faIcon = FaIcons.ArrowLeft,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .constrainAs(back) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable { navBack.invoke() }
            )

            Text(text = context.getString(R.string.sign_up),
                style = TextStyle(
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .constrainAs(text){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }


//        MasterHaulerLogoSmall()

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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = terms.value,
                onCheckedChange = {
                    terms.value = !terms.value
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                    checkmarkColor = MaterialTheme.colorScheme.background,
                )
            )

            Text(text = context.getString(R.string.terms_agree),
                style = MaterialTheme.typography.bodySmall
            )

            Text(text = context.getString(R.string.terms_and_conditions),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .clickable {
                        Toast.makeText(context, "Terms and conditions", Toast.LENGTH_SHORT).show()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        B18PrimaryBodyMedRound(
            click = {
                when(true){
                    firstName.value.isNullOrEmpty() -> { errorText.value = "Please enter your first name."}
                    lastName.value.isNullOrEmpty() -> { errorText.value = "Please enter your last name."}
                    email.value.isNullOrEmpty() -> {errorText.value = "Please enter your email."}
                    password.value.isNullOrEmpty() -> { errorText.value = "Please enter your password."}
                    confirm.value.isNullOrEmpty() -> { errorText.value = "Please enter your password confirmation."}
                    !terms.value -> { errorText.value = "Please review our Terms and Conditions and accept before continuing."}
                    else -> {
                        progress.value = true
                        register.invoke()
                    }
                }
            },
            text = "Submit",
            textStyle = MaterialTheme.typography.bodyMedium
        )
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
            navBack = {},
            errorText = remember { mutableStateOf(null) }
        )
    }
}

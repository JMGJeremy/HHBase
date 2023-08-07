package com.jmg.baseproject.accessClasses.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.jmg.baseproject.R
import com.jmg.baseproject.ui.auth.BaseLoginViewModel
import com.jmg.baseproject.ui.auth.RegisterScreen
import retrofit2.Response


class RegisterClass(
    baseUrl: String,
) {

    private val viewModel = BaseLoginViewModel(baseUrl = baseUrl)
    private val selected = mutableStateOf("Parent")

    @Composable
    fun GetRegisterScreen(
        first : MutableState<String?>,
        last: MutableState<String?>,
        email: MutableState<String?>,
        pass: MutableState<String?>,
        confirm: MutableState<String?>,
        err: MutableState<String?>,
        resp: MutableState<Response<Any?>?>,
        optionOne: String,
        optionTwo: String,
        logoInt: Int,
        back: ()->Unit,
        terms: ()->Unit
        ){
        RegisterScreen(
            firstName = first,
            lastName = last,
            email = email,
            password = pass,
            confirm = confirm,
            register = { viewModel.registerUser(
                email = email.value,
                pass = pass.value,
                confirm = confirm.value,
                first = first.value,
                last = last.value,
                error = err,
                resp = resp,
                type = selected.value.lowercase()
            ) },
            errorText = err,
            logo = logoInt,
            back = back,
            termsClick = terms,
            option1 = optionOne,
            option2 = optionTwo,
            selected = selected
            )
    }
}
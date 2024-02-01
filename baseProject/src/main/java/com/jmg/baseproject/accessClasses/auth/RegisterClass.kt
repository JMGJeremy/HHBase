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
        setError: (String?)->Unit,
        resp: MutableState<Response<Any?>?>,
        optionOne: String,
        optionTwo: String,
        back: ()->Unit,
        terms: ()->Unit,
        zip: MutableState<String?>,
        setProgress: (Boolean)->Unit,
        setEmail: (String?)->Unit,
        setInput: (String?)->Unit,
        setLastName: (String?)->Unit,
        setName: (String?)->Unit,
        setZip: (String?)->Unit,
        ){
        RegisterScreen(
            firstName = first,
            lastName = last,
            email = email,
            password = pass,
            confirm = confirm,
            register = {

                viewModel.registerUser(
                    email = email.value,
                    pass = pass.value,
                    confirm = confirm.value,
                    first = first.value,
                    last = last.value,
                    setError = {setError.invoke(it)},
                    resp = resp,
                    type = selected.value.lowercase(),
                    zip = zip.value
                )
                       },
            setError = {setError.invoke(it)},
            back = back,
            termsClick = terms,
            option1 = optionOne,
            option2 = optionTwo,
            selected = selected,
            zip = zip,
            setProgress = {
                setProgress.invoke(it)
            },
            setInput = {setInput.invoke(it)},
            setEmail = {setEmail.invoke(it)},
            setZip = {setZip.invoke(it)},
            setName = {setName.invoke(it)},
            setLastName = {setLastName.invoke(it)}

        )
    }
}
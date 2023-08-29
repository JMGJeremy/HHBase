package com.jmg.baseproject.ui.auth

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.jmg.baseproject.models.auth.*
import com.jmg.baseproject.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BaseLoginViewModel(baseUrl: String): ViewModel() {

    private val TAG = "BaseLoginViewModel"

    private val service = ApiService.getInstance(baseUrl = baseUrl)


    fun loginUser(
        email: String,
        password: String,
        response: MutableState<Response<Any?>?>,
        error: MutableState<String?>
    ){
        val login = LoginApi(
            email = email,
            password = password,
            grantType = "password"
        )
        Log.e(TAG, "login = $login")
        service.loginUser(
            login = login
        ).enqueue(object: Callback<Any?> {
            override fun onResponse(call: Call<Any?>, r: Response<Any?>) {
                response.value = r
                Log.e(TAG, "Base login response = ${response.value?.body()} + $r")
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                error.value = t.localizedMessage
                Log.e(TAG, "Base login response error = ${error.value} + $t")
            }
        })
    }

    fun registerUser(
        email: String?,
        pass: String?,
        confirm: String?,
        first: String?,
        last: String?,
        resp: MutableState<Response<Any?>?>,
        error: MutableState<String?>,
        type: String,
        zip: String?
    ) {
        service.registerUser(
            user = RegisterUserApi(
                RegisterUser(
                    firstName = first,
                    lastName = last,
                    email = email,
                    password = pass,
                    confirmation = confirm,
                    type = type,
                    under18 = false,
                    zip = zip
                )
            )
        ).enqueue(object: Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                resp.value = response
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }
}
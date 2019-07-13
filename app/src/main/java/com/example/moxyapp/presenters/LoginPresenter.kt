package com.example.moxyapp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.moxyapp.interfaces.ServiceInterface
import com.example.moxyapp.models.Token
import com.example.moxyapp.views.LoginView
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun onLoginButtonClick(usernameText: String, passwordText: String) {
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            viewState.showError("please fill all places")
        } else
            viewState.startLogin()

    }

    fun createRetrofit(){
        viewState.retrofitCreate()
    }

    fun onResponse(response: Response<Token>) {
        if (response.code() == 200) {
            val token = response.body()!!
            Log.d("SUCCESS", token.token)
            viewState.showSuccess()
            viewState.changeActivity()

        } else {
            Log.d("NOT", response.message())
            viewState.showError(response.message())
        }
    }

    fun onFailure(t: Throwable) {
        t.message?.let { viewState.showError(it) }
    }


}
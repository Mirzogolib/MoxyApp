package com.example.moxyapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.moxyapp.R
import com.example.moxyapp.interfaces.ServiceInterface
import com.example.moxyapp.models.ProductsList
import com.example.moxyapp.models.Token
import com.example.moxyapp.presenters.LoginPresenter
import com.example.moxyapp.views.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var loginPresenter: LoginPresenter
    lateinit var usernameText: String
    lateinit var passwordText: String
    lateinit var retrofit: Retrofit
    lateinit var service: ServiceInterface

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter.createRetrofit()
        login_button.setOnClickListener {
            usernameText = username_edit_text.text.toString()
            passwordText = password_edit_text.text.toString()
            loginPresenter.onLoginButtonClick(usernameText, passwordText)

        }


    }


    override fun startLogin() {
        val loginUser = service.login(usernameText, passwordText)
        loginUser.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
               loginPresenter.onResponse(response)
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                loginPresenter.onFailure(t)
            }
        })
    }

   override fun retrofitCreate(){
        retrofit = Retrofit.Builder()
            .baseUrl("http://apipost.ritm.uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ServiceInterface::class.java)
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }

    override fun showError(message: Int) {
        Toast.makeText(applicationContext, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        Toast.makeText(applicationContext, getString(R.string.success), Toast.LENGTH_SHORT).show()

    }

    override fun changeActivity() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)

    }

}

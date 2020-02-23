package com.sanushradalage.cinema.user.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sanushradalage.cinema.R
import com.sanushradalage.cinema.databinding.ActivityLoginBinding
import com.sanushradalage.cinema.user.models.User
import com.sanushradalage.cinema.user.viewmodels.UserViewModel
import com.sanushradalage.cinema.user.repositories.UserViewModelFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val loginViewModel : UserViewModel = ViewModelProvider(this,
            UserViewModelFactory(this, User())
        ).get(UserViewModel::class.java)

        binding.loginViewModel = loginViewModel

    }
}

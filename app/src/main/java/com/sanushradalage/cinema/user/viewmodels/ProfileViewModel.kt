package com.sanushradalage.cinema.user.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.sanushradalage.cinema.user.views.LoginActivity

class ProfileViewModel(private val context: Context): ViewModel() {

    fun onLogoutClick(){
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
    }

}
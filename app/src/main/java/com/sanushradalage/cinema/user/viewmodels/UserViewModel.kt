package com.sanushradalage.cinema.user.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanushradalage.cinema.movies.views.MainActivity
import com.sanushradalage.cinema.user.models.User

class UserViewModel(private val context: Context, private val user: User): ViewModel() {

    var email:MutableLiveData<String> = MutableLiveData()
    var password:MutableLiveData<String> = MutableLiveData()

    lateinit var toast :Toast

    fun onLoginClick() {

        user.setEmail(email.value.toString())
        user.setPassword(password.value.toString())

        if (!user.isValidEmail)
        {
          toast = Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 10)
            toast.show()
        }
        else if (!user.isValidPassword)
        {
            Toast.makeText(context, "Password Should be Minimum 6 Characters", Toast.LENGTH_SHORT).show()
        }
        else if (user.isValidCredential)
        {
            Handler().postDelayed({
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
            }, 500)
        }
        else
        {
            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
        }
    }
}

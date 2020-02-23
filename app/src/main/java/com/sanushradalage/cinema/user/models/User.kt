package com.sanushradalage.cinema.user.models

import android.text.TextUtils
import android.util.Patterns
import java.io.Serializable

class User: Serializable {

    private var email:String? = null
    private var password:String? = null

    fun getEmail():String {
        return this.email.toString()
    }
    fun setEmail(email:String) {
        this.email = email
    }
    fun getPassword():String {
        return this.password.toString()
    }
    fun setPassword(password:String) {
        this.password = password
    }

    val isValidEmail:Boolean
        get() {
            if (this.email != null &&!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches())
            {
                return true
            }
            return false
        }
    val isValidPassword:Boolean
        get() {
            if (this.password != null && this.password!!.length >= 6)
            {
                return true
            }
            return false
        }
    val isValidCredential:Boolean
        get() {
            if (this.email.equals("maleekasanush@gmail.com", ignoreCase = true) && this.password == "123456")
            {
                return true
            }
            return false
        }
}
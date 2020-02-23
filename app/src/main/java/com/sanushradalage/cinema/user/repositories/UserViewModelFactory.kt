package com.sanushradalage.cinema.user.repositories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanushradalage.cinema.user.models.User
import com.sanushradalage.cinema.user.viewmodels.UserViewModel

class UserViewModelFactory(private var context: Context, private var user: User): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass:Class<T>):T {
        return UserViewModel(context, user) as T
    }
}
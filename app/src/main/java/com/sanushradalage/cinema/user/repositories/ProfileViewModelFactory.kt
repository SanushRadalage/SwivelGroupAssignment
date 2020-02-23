package com.sanushradalage.cinema.user.repositories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanushradalage.cinema.user.viewmodels.ProfileViewModel

class ProfileViewModelFactory(private var context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass:Class<T>):T {
        return ProfileViewModel(context) as T
    }
}
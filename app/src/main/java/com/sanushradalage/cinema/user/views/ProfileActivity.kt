package com.sanushradalage.cinema.user.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sanushradalage.cinema.R
import com.sanushradalage.cinema.databinding.ActivityProfileBinding
import com.sanushradalage.cinema.user.models.User
import com.sanushradalage.cinema.user.repositories.ProfileViewModelFactory
import com.sanushradalage.cinema.user.repositories.UserViewModelFactory
import com.sanushradalage.cinema.user.viewmodels.ProfileViewModel
import com.sanushradalage.cinema.user.viewmodels.UserViewModel

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        val profileViewModel : ProfileViewModel = ViewModelProvider(this,
            ProfileViewModelFactory(this)
        ).get(ProfileViewModel::class.java)

        binding.profileViewModel = profileViewModel
    }
}

package com.sanushradalage.cinema.extras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanushradalage.cinema.R
import com.sanushradalage.cinema.movies.views.MainActivity
import com.sanushradalage.cinema.user.views.LoginActivity

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this@ActivitySplash, LoginActivity::class.java))
        // close splash activity
        finish()
    }
}

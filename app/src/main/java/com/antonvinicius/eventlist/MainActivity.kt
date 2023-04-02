package com.antonvinicius.eventlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antonvinicius.eventlist.screens.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToHomeActivity()
    }

    private fun navigateToHomeActivity() {
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
    }
}
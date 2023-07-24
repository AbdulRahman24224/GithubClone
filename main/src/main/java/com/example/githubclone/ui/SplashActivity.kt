package com.example.githubclone.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.app.list.ReposListActivity

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, ReposListActivity::class.java))
        finish()
    }
}
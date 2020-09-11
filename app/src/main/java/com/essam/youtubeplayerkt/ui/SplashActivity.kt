package com.essam.youtubeplayerkt.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.essam.youtubeplayerkt.R
import com.essam.youtubeplayerkt.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // just give it one second to display a splash screen
        Thread.sleep(1000)

        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }
}
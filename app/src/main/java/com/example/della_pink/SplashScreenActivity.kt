package com.example.della_pink

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.jvm.java

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {

            delay(2000)

            startActivity(
                Intent(
                    this@SplashScreenActivity,
                    LoginActivity::class.java
                )
            )

            finish()
        }
    }
}
package com.example.della_pink

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.della_pink.tutorial.TutorialMessageActivity // Pastikan mengimpor kelas tutorial ini
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            // Delay splash screen selama 2 detik
            delay(2000)

            startActivity(
                Intent(
                    this@SplashScreenActivity,
                    TutorialMessageActivity::class.java
                )
            )

            // Menutup SplashScreenActivity
            finish()
        }
    }
}
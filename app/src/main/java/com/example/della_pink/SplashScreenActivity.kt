package com.example.della_pink

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.tutorial.TutorialMessageActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🟢 MENGGUNAKAN LAYOUT UTUH KAMU (Tidak diganti)
        setContentView(R.layout.activity_splash_screen)

        // Jeda 3 detik agar ProgressBar kamu sempat berputar sebelum pindah halaman
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, TutorialMessageActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
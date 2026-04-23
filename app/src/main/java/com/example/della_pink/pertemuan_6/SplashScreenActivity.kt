package com.example.della_pink.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.della_pink.pertemuan_3.LoginActivity
import com.example.della_pink.pertemuan_4.MenuUtamaActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.della_pink.R.layout.activity_splash_screen)

        // Mengambil status login dari SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000)

            // Alur: Jika sudah login -> Main, Jika belum -> Login
            val intent = if (isLogin) {
                Intent(this@SplashScreenActivity, MenuUtamaActivity::class.java)
            } else {
                Intent(this@SplashScreenActivity, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }
    }
}
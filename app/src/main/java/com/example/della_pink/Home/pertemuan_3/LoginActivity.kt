package com.example.della_pink.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.AuthActivity
import com.example.della_pink.BaseActivity
import com.example.della_pink.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LOGIN
        binding.btnLogin.setOnClickListener {

            val username =
                binding.inputUsername.text.toString().trim()

            val password =
                binding.inputPassword.text.toString().trim()

            val pref =
                getSharedPreferences("user_pref", MODE_PRIVATE)

            val savedUsername =
                pref.getString("username", "")

            val savedPassword =
                pref.getString("password", "")

            if (username == savedUsername &&
                password == savedPassword
            ) {

                pref.edit().apply {
                    putBoolean("isLogin", true)
                    apply()
                }

                startActivity(
                    Intent(
                        this,
                        BaseActivity::class.java
                    )
                )

                finish()

            } else {

                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password salah")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        // DAFTAR DISINI
        binding.txtDaftar.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AuthActivity::class.java
                )
            )
        }
    }
}
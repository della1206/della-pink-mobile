package com.example.della_pink.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityWelcomeBinding
import com.example.della_pink.pertemuan_4.MenuUtamaActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik pada Card Petani masuk ke Menu Utama
        binding.cardPetani.setOnClickListener {
            val intent = Intent(this, MenuUtamaActivity::class.java)
            startActivity(intent)
        }
    }
}
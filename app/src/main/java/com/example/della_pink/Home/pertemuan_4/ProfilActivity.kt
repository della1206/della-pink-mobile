package com.example.della_pink.Home.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namaUser = intent.getStringExtra("nama_user")

        binding.tvNamaUser.text = namaUser ?: "Della Marcelina"

        // Handle Tombol Logout
        binding.btnLogout.setOnClickListener {
            finish()
        }

        binding.btnSettings.setOnClickListener {

        }
    }
}
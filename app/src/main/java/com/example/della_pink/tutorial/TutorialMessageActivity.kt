package com.example.della_pink.tutorial

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    // Menerapkan ViewBinding sesuai instruksi modul
    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan View Binding
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            // 1. Setup daftar fragment yang akan ditampilkan di Onboarding
            val fragmentsList = listOf(
                Tutorial1Fragment(),
                Tutorial2Fragment(),
                Tutorial3Fragment()
            )

            // 2. Pasang adapter ke ViewPager2
            val adapter = TutorialFragmentAdapter(this, fragmentsList)
            binding.tutorialMessageViewPager.adapter = adapter

            // 3. Hubungkan DotsIndicator dengan ViewPager2
            binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)

        } catch (e: Exception) {
            // Jika ada fragment atau XML yang error, aplikasi tidak akan crash/keeps stopping
            Log.e("TutorialError", "Terjadi kesalahan saat memuat komponen onboarding: ${e.message}")
            e.printStackTrace()
            Toast.makeText(
                this,
                "Gagal memuat halaman tutorial. Cek Logcat dengan keyword 'TutorialError'!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
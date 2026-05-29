package com.example.della_pink.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    // Menerapkan ViewBinding sesuai instruksi modul
    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup daftar fragment yang akan ditampilkan di Onboarding
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        // 2. Pasang adapter ke ViewPager2
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter

        // 3. Hubungkan DotsIndicator dengan ViewPager2 (Sesuai langkah Modul nomor 3)
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}
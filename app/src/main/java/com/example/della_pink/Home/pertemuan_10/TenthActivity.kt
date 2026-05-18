package com.example.della_pink.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pengelolaan Perangkat Desa"
            setDisplayHomeAsUpEnabled(true)
        }


        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Pasang Adapter ke ViewPager2
        val tabsAdapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout dengan ViewPager2 (TabLayoutMediator)
        // Menyesuaikan nama-nama Tab sesuai dengan modul dan tema Bina Desa
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true // Badge titik kosong (sesuai modul)
                }
                1 -> {
                    tab.text = "Tab B"
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5 // Badge dengan angka 5 (sesuai modul)
                }
                2 -> {
                    tab.text = "Aparat & Lembaga" // Mengelola data RT/RW/Lembaga
                }
            }
        }.attach()
    }
}
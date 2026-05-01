package com.example.della_pink.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityDashboardPanenBinding

class DashboardPanenActivity : AppCompatActivity() {

    // Inisialisasi ViewBinding sesuai standar Pertemuan 3 & 4
    private lateinit var binding: ActivityDashboardPanenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup ViewBinding
        binding = ActivityDashboardPanenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LOGIC SEARCH (Opsional)
        binding.searchPanen.setOnClickListener {
            Toast.makeText(this, "Fitur pencarian ditekan", Toast.LENGTH_SHORT).show()
        }

        // LOGIC KERANJANG
        binding.ivCart.setOnClickListener {
            Toast.makeText(this, "Keranjang masih kosong", Toast.LENGTH_SHORT).show()
        }
    }
}
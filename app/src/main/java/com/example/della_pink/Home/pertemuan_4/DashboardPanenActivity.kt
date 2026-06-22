package com.example.della_pink.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityDashboardPanenBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DashboardPanenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardPanenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardPanenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Snackbar.make(
            binding.root,
            "🌾 Selamat datang di Dashboard Panen",
            Snackbar.LENGTH_SHORT
        ).show()

        binding.searchPanen.setOnClickListener {
            Snackbar.make(
                binding.root,
                "🔍 Fitur pencarian ditekan",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding.ivCart.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("🛒 Keranjang")
                .setMessage("Keranjang Anda masih kosong. Yuk mulai belanja!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("Lihat Produk") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(
                        binding.root,
                        "📦 Mengarahkan ke halaman produk",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "onStart: DashboardPanenActivity terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "onDestroy: DashboardPanenActivity dihapus dari stack")
    }
}
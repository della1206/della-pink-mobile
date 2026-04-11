package com.example.della_pink.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityMenuUtamaBinding
import com.example.della_pink.pertemuan_2.SecondActivity
import com.example.della_pink.pertemuan_3.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MenuUtamaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuUtamaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRumus.setOnClickListener {
            // Memanggil SecondActivity dari package pertemuan_2
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            Log.e("Navigation", "Menuju Halaman Rumus Bangun Ruang")
        }

        binding.btnProfil.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            intent.putExtra("nama_user", "Della Marcelina")
            startActivity(intent)
        }

        binding.btnDashboard.setOnClickListener {
            val intent = Intent(this, DashboardPanenActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("YA") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("BATAL") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    // Lifecycle Log untuk memantau aktivitas sesuai modul Pertemuan 4
    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "onStart: MenuUtamaActivity terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "onDestroy: MenuUtamaActivity dihapus dari stack")
    }
}
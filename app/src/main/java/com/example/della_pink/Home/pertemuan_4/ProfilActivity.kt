package com.example.della_pink.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityProfilBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class ProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namaUser = intent.getStringExtra("nama_user")
        binding.tvNamaUser.text = namaUser ?: "Della Marcelina"

        Snackbar.make(
            binding.root,
            "👋 Selamat datang, ${binding.tvNamaUser.text}",
            Snackbar.LENGTH_SHORT
        ).show()

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari profil?")
                .setPositiveButton("Ya, Logout") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(
                        binding.root,
                        "✅ Logout berhasil",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.btnSettings.setOnClickListener {
            Snackbar.make(
                binding.root,
                "⚙️ Pengaturan akan segera hadir",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "onStart: ProfilActivity terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "onDestroy: ProfilActivity dihapus dari stack")
    }
}
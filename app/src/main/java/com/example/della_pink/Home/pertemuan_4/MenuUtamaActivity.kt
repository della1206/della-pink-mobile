package com.example.della_pink.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityMenuUtamaBinding
import com.example.della_pink.Home.pertemuan_2.SecondActivity
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MenuUtamaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuUtamaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Menu Perangkat Desa"

        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // 🟢 FIXED: Sekarang diarahkan dengan benar ke ProfilActivity (bukan Fragment lagi)
        binding.btnProfil.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            // Mengambil username dari SharedPreferences untuk dikirim ke Profil
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val username = sharedPref.getString("username", "Della Marcelina")
            intent.putExtra("nama_user", username)
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
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "onStart: MenuUtamaActivity terlihat")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "onDestroy: MenuUtamaActivity dihapus dari stack")
    }
}
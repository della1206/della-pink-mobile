package com.example.della_pink

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingActivity : AppCompatActivity() {

    private lateinit var lvAbout: ListView
    private val listData = arrayOf(
        "Tentang Program Bina Desa",
        "Kebijakan Privasi",
        "Syarat dan Ketentuan",
        "Bantuan",
        "Versi Aplikasi v1.0"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setting)
        setupInsets()
        setupToolbar()
        initView()
        setupListView()
    }
    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
    private fun setupToolbar() {
        val toolbar =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tentang Aplikasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    private fun initView() {
        lvAbout = findViewById(R.id.lvAbout)
    }
    private fun setupListView() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listData
        )
        lvAbout.adapter = adapter
        lvAbout.setOnItemClickListener { _, _, position, _ ->
            val title = listData[position]
            Toast.makeText(
                this,
                "Membuka: $title",
                Toast.LENGTH_SHORT
            ).show()
            val message = when (title) {
                "Tentang Program Bina Desa" -> {
                    "Program ini bertujuan untuk membantu digitalisasi administrasi desa agar pelayanan masyarakat menjadi lebih cepat dan modern."
                }
                "Kebijakan Privasi" -> {
                    "Data pengguna dijaga dengan aman dan tidak dibagikan kepada pihak lain tanpa izin."
                }
                "Syarat dan Ketentuan" -> {
                    "Pengguna wajib menggunakan data asli sesuai identitas desa."
                }
                "Bantuan" -> {
                    "Silakan hubungi admin desa atau email support@binadesa.id"
                }
                "Versi Aplikasi v1.0" -> {
                    "Versi terbaru dengan fitur Dashboard Panen, WebView Desa, dan Menu Informasi."
                }
                else -> {
                    "Informasi belum tersedia."
                }
            }
            showDialog(title, message)
        }
    }
    private fun showDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("TUTUP") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
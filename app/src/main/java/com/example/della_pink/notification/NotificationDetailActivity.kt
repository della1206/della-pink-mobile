package com.example.della_pink.notification

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityNotificationDetailBinding

class NotificationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = " Detail Notifikasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("notification_title") ?: "Informasi Bina Desa"
        val message = intent.getStringExtra("notification_message") ?: "Selamat datang di aplikasi Bina Desa"
        val fromNotification = intent.getBooleanExtra("from_notification", false)

        binding.tvTitle.text = title
        binding.tvMessage.text = message

        binding.tvSource.text = if (fromNotification) {
            "📨 Dari Notifikasi"
        } else {
            "📱 Dari Aplikasi"
        }
        binding.btnAction.setOnClickListener {
            Toast.makeText(this, "Terima kasih telah menggunakan Bina Desa! 🏡", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, """
                    📢 *Informasi Bina Desa*
                    
                    $title
                    $message
                    
                    ---
                    Aplikasi Bina Desa - Membangun Desa Bersama
                """.trimIndent())
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan Informasi"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
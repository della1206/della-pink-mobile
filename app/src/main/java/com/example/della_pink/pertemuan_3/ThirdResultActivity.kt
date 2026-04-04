package com.example.della_pink.pertemuan_3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.della_pink.R
import com.example.della_pink.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("data")

        binding.textHasil.text = "Kode berhasil dikirim ke $data"
    }
}


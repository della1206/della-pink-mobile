package com.example.della_pink.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            val isi = binding.inputNoTujuan.text.toString()

            val intent = Intent(this, ThirdResultActivity::class.java)
            intent.putExtra("data", isi)
            startActivity(intent)
        }
    }
}
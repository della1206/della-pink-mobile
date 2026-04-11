package com.example.della_pink.pertemuan_2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivitySecondBinding // Pastikan nama layoutnya activity_second.xml

class SecondActivity : AppCompatActivity() {

    // Menggunakan ViewBinding sesuai standar Pertemuan 3 & 4
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // HITUNG SEGITIGA (Menggunakan binding)
        binding.btnHitungSegitiga.setOnClickListener {
            val alas = binding.inputAlas.text.toString().toDoubleOrNull()
            val tinggi = binding.inputTinggi.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {
                val hasil = 0.5 * alas * tinggi
                binding.textHasil.text = "Luas Segitiga = $hasil"
            } else {
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        // HITUNG KUBUS
        binding.btnHitungKubus.setOnClickListener {
            val sisi = binding.inputSisi.text.toString().toDoubleOrNull()

            if (sisi != null) {
                val hasil = sisi * sisi * sisi
                binding.textHasil.text = "Volume Kubus = $hasil"
            } else {
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
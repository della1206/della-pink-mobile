package com.example.della_pink.pertemuan_2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // INISIALISASI KOMPONEN
        val inputAlas = findViewById<EditText>(R.id.inputAlas)
        val inputTinggi = findViewById<EditText>(R.id.inputTinggi)
        val inputSisi = findViewById<EditText>(R.id.inputSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnHitungKubus)

        val textHasil = findViewById<TextView>(R.id.textHasil)


        // HITUNG SEGITIGA
        btnSegitiga.setOnClickListener {

            val alas = inputAlas.text.toString().toDoubleOrNull()
            val tinggi = inputTinggi.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {
                val hasil = 0.5 * alas * tinggi
                textHasil.text = "Luas Segitiga = $hasil"
            } else {
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        // HITUNG KUBUS
        btnKubus.setOnClickListener {

            val sisi = inputSisi.text.toString().toDoubleOrNull()

            if (sisi != null) {
                val hasil = sisi * sisi * sisi
                textHasil.text = "Volume Kubus = $hasil"
            } else {
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
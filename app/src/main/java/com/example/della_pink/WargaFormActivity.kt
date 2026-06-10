package com.example.della_pink

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.data.AppDatabase
import com.example.della_pink.data.entity.WargaEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WargaFormActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warga_form)

        database = AppDatabase.getInstance(this)

        val etNik = findViewById<EditText>(R.id.etNikWarga)
        val etNama = findViewById<EditText>(R.id.etNamaWarga)
        val etJenisKelamin = findViewById<EditText>(R.id.etJenisKelaminWarga)
        val etAlamat = findViewById<EditText>(R.id.etAlamatWarga)
        val btnSimpan = findViewById<Button>(R.id.btnSimpanWarga)

        btnSimpan.setOnClickListener {
            val inputNik = etNik.text.toString()
            val inputNama = etNama.text.toString()
            val inputJK = etJenisKelamin.text.toString()
            val inputAlamat = etAlamat.text.toString()

            if (inputNik.isNotEmpty() && inputNama.isNotEmpty() && inputJK.isNotEmpty() && inputAlamat.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val wargaBaru = WargaEntity(
                            id = 0,
                            nama = inputNama,
                            nik = inputNik,
                            jenisKelamin = inputJK,
                            alamat = inputAlamat
                        )

                        database.wargaDao().insertWarga(wargaBaru)

                        runOnUiThread {
                            Toast.makeText(this@WargaFormActivity, "Sukses! Data Warga Masuk Room Database", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@WargaFormActivity, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Semua kolom form kependudukan wajib diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
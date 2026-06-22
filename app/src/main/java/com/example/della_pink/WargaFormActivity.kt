package com.example.della_pink

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.data.AppDatabase
import com.example.della_pink.data.entity.WargaEntity
import com.example.della_pink.databinding.ActivityWargaFormBinding  // ✅ TAMBAHKAN
import com.google.android.material.dialog.MaterialAlertDialogBuilder  // ✅ TAMBAHKAN
import com.google.android.material.snackbar.Snackbar  // ✅ TAMBAHKAN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WargaFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWargaFormBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWargaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)

        binding.btnSimpanWarga.setOnClickListener {
            val inputNik = binding.etNikWarga.text.toString()
            val inputNama = binding.etNamaWarga.text.toString()
            val inputJK = binding.etJenisKelaminWarga.text.toString()
            val inputAlamat = binding.etAlamatWarga.text.toString()

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
                            Snackbar.make(
                                binding.root,
                                "✅ Sukses! Data Warga Masuk Room Database",
                                Snackbar.LENGTH_LONG
                            ).show()

                            MaterialAlertDialogBuilder(this@WargaFormActivity)
                                .setTitle("✅ Berhasil!")
                                .setMessage("Data warga atas nama $inputNama berhasil disimpan!")
                                .setPositiveButton("OK") { dialog, _ ->
                                    dialog.dismiss()
                                    finish()
                                }
                                .show()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@WargaFormActivity, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "⚠️ Semua kolom form kependudukan wajib diisi!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
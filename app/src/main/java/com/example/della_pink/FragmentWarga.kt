package com.example.della_pink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.della_pink.data.AppDatabase
import com.example.della_pink.data.entity.WargaEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentWarga : Fragment() {

    private lateinit var database: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_warga, container, false)
        database = AppDatabase.getInstance(requireContext())

        val etNik = view.findViewById<EditText>(R.id.etNikWarga)
        val etNama = view.findViewById<EditText>(R.id.etNamaWarga)
        val etJenisKelamin = view.findViewById<EditText>(R.id.etJenisKelaminWarga)
        val etAlamat = view.findViewById<EditText>(R.id.etAlamatWarga)
        val btnSimpan = view.findViewById<Button>(R.id.btnSimpanWarga)

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

                        activity?.runOnUiThread {
                            Toast.makeText(requireContext(), "Sukses! Data Warga Tersimpan di Room", Toast.LENGTH_LONG).show()
                            etNik.text.clear()
                            etNama.text.clear()
                            etJenisKelamin.text.clear()
                            etAlamat.text.clear()
                        }
                    } catch (e: Exception) {
                        activity?.runOnUiThread {
                            Toast.makeText(requireContext(), "Gagal: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Semua kolom form kependudukan wajib diisi!", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
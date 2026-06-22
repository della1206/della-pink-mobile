package com.example.della_pink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.della_pink.data.AppDatabase
import com.example.della_pink.data.entity.WargaEntity
import com.example.della_pink.databinding.FragmentWargaBinding  // ✅ TAMBAHKAN
import com.google.android.material.dialog.MaterialAlertDialogBuilder  // ✅ TAMBAHKAN
import com.google.android.material.snackbar.Snackbar  // ✅ TAMBAHKAN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentWarga : Fragment() {

    private var _binding: FragmentWargaBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWargaBinding.inflate(inflater, container, false)
        database = AppDatabase.getInstance(requireContext())

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

                        activity?.runOnUiThread {
                            Snackbar.make(
                                binding.root,
                                "✅ Sukses! Data Warga Tersimpan di Room",
                                Snackbar.LENGTH_LONG
                            ).show()

                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle("✅ Berhasil!")
                                .setMessage("Data warga atas nama $inputNama berhasil disimpan!")
                                .setPositiveButton("OK") { dialog, _ ->
                                    dialog.dismiss()
                                    binding.etNikWarga.text.clear()
                                    binding.etNamaWarga.text.clear()
                                    binding.etJenisKelaminWarga.text.clear()
                                    binding.etAlamatWarga.text.clear()
                                }
                                .show()
                        }
                    } catch (e: Exception) {
                        activity?.runOnUiThread {
                            Toast.makeText(requireContext(), "Gagal: ${e.message}", Toast.LENGTH_SHORT).show()
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
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
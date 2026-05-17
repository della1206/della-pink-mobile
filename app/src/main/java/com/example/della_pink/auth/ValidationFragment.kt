package com.example.della_pink.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.R
import com.example.della_pink.databinding.FragmentValidationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ValidationFragment : Fragment(R.layout.fragment_validation) {

    private var _binding: FragmentValidationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentValidationBinding.bind(view)

        val pref = requireContext()
            .getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // tampilkan data
        binding.tvValNama.text =
            "Nama: ${pref.getString("nama", "")}"

        binding.tvValEmail.text =
            "Email: ${pref.getString("email", "")}"

        binding.tvValTglLahir.text =
            "Tgl Lahir: ${pref.getString("tgl_lahir", "")}"

        binding.tvValGender.text =
            "Gender: ${pref.getString("gender", "")}"

        binding.tvValUsername.text =
            "Username: ${pref.getString("username", "")}"

        // tombol submit
        binding.btnSubmit.setOnClickListener {

            val pass =
                pref.getString("password", "") ?: ""

            val confirm =
                pref.getString("confirm_password", "") ?: ""

            val nama =
                pref.getString("nama", "") ?: ""

            // validasi
            if (nama.isEmpty() || pass.isEmpty()) {

                Toast.makeText(
                    context,
                    "Semua inputan tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (pass != confirm) {

                Toast.makeText(
                    context,
                    "Password dan Confirm Password harus sama!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Info")
                    .setMessage("Registrasi Berhasil")

                    .setPositiveButton("OK") { _, _ ->

                        // pindah ke LoginActivity
                        startActivity(
                            Intent(
                                requireContext(),
                                LoginActivity::class.java
                            )
                        )

                        requireActivity().finish()
                    }

                    .show()
            }
        }

        // kembali ke register
        binding.btnKembali.setOnClickListener {

            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
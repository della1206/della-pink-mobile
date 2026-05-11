package com.example.della_pink.auth

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.della_pink.R
import com.example.della_pink.databinding.FragmentRegisterBinding
import java.util.*

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 1. Logika DatePicker
        binding.etTanggalLahir.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(requireContext(), { _, year, month, day ->
                binding.etTanggalLahir.setText("$day/${month + 1}/$year")
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        // 2. Simpan & Lanjut (Soal b1)
        binding.btnRegister.setOnClickListener {
            val pref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val gender = if (binding.rbPria.isChecked) "Laki-laki" else "Perempuan"

            pref.edit().apply {
                putString("nama", binding.etNama.text.toString())
                putString("email", binding.etEmail.text.toString())
                putString("tgl_lahir", binding.etTanggalLahir.text.toString())
                putString("gender", gender)
                putString("username", binding.etUsername.text.toString())
                putString("password", binding.etPassword.text.toString())
                putString("confirm_password", binding.etConfirmPassword.text.toString())
                apply()
            }

            findNavController().navigate(R.id.action_registerFragment_to_validationFragment)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
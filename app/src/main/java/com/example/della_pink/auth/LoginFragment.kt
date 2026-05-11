package com.example.della_pink.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.della_pink.BaseActivity // Sesuaikan dengan Home Activity kamu
import com.example.della_pink.R
import com.example.della_pink.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pindah ke Register
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Ambil data dari SharedPreferences (File: user_pref)
            val pref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val savedUser = pref.getString("username", "")
            val savedPass = pref.getString("password", "")

            // Kondisi Login (Soal b3)
            val isDefaultLogin = (username == password && username.isNotEmpty())
            val isRegisteredLogin = (username == savedUser && password == savedPass && username.isNotEmpty())

            if (isDefaultLogin || isRegisteredLogin) {
                // Simpan status login
                pref.edit().putBoolean("isLogin", true).apply()

                // Pindah ke Home
                startActivity(Intent(requireContext(), BaseActivity::class.java))
                requireActivity().finish()
            } else {
                // Tampilkan Error (Soal b3)
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password salah")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
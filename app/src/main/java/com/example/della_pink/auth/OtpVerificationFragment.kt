package com.example.della_pink.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.della_pink.BaseActivity
import com.example.della_pink.SharedPrefManager
import com.example.della_pink.databinding.FragmentOtpVerificationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OtpVerificationFragment : Fragment() {

    private var _binding:
            FragmentOtpVerificationBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentOtpVerificationBinding.inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        val nama =
            arguments?.getString("nama")

        val phone =
            arguments?.getString("phone")

        val username =
            arguments?.getString("username")

        val password =
            arguments?.getString("password")

        binding.btnVerify.setOnClickListener {

            val otp =
                binding.etOtp.text.toString()

            // OTP BENAR
            if (
                otp.isNotEmpty() &&
                otp == phone
            ) {

                val pref =
                    SharedPrefManager(requireContext())

                pref.saveUser(
                    nama ?: "",
                    phone ?: "",
                    username ?: "",
                    password ?: ""
                )

                startActivity(
                    Intent(
                        requireContext(),
                        BaseActivity::class.java
                    )
                )

                requireActivity().finish()
            }

            // OTP SALAH
            else {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("OTP Salah")
                    .setMessage(
                        "Kode OTP tidak sesuai"
                    )
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
package com.example.della_pink.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {

    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)

        binding.btnAyoMulai.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            activity?.finish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
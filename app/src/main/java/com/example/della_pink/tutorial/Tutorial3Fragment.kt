package com.example.della_pink.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.R

class Tutorial3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Menghubungkan fragment dengan layout XML (fragment_tutorial3.xml)
        val view = inflater.inflate(R.layout.fragment_tutorial3, container, false)

        // 2. Inisialisasi tombol "Ayo Mulai" berdasarkan ID di XML
        val btnAyoMulai = view.findViewById<Button>(R.id.btnAyoMulai)

        // 3. Memberikan aksi ketika tombol diklik
        btnAyoMulai.setOnClickListener {
            // Berpindah dari fragment ini ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            // Menutup TutorialMessageActivity agar ketika user berada di halaman Login
            // dan menekan tombol 'Back', mereka tidak akan kembali ke halaman Onboarding lagi.
            activity?.finish()
        }

        return view
    }
}
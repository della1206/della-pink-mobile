package com.example.della_pink.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.della_pink.Home.pertemuan_2.SecondActivity
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.Home.pertemuan_4.DashboardPanenActivity
import com.example.della_pink.Home.pertemuan_4.ProfilActivity
import com.example.della_pink.databinding.FragmentHomeBinding
import com.example.della_pink.WebViewActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inisialisasi View Binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Konfigurasi Toolbar sebagai ActionBar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Home"

        // 1. Navigasi ke Rumus Bangun Ruang (SecondActivity)
        binding.btnRumus.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        // 2. Navigasi ke Profil Pengguna (ProfilActivity)
        binding.btnProfilPengguna.setOnClickListener {
            val intent = Intent(requireContext(), ProfilActivity::class.java)
            startActivity(intent)
        }

        binding.btnPanen.setOnClickListener {
            val intent = Intent(requireContext(), DashboardPanenActivity::class.java)
            startActivity(intent)
        }
        binding.btnWebView.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("url", "https://perangkat.alwaysdata.net/")
            startActivity(intent)
            }

        // 4. Fitur Logout mengarah ke LoginActivity
        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            // Menghapus tumpukan activity agar tidak bisa kembali ke Home setelah logout
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Membersihkan binding untuk mencegah kebocoran memori
        _binding = null
    }
}
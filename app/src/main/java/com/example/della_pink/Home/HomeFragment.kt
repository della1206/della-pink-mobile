package com.example.della_pink.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.della_pink.Home.pertemuan_10.TenthActivity
import com.example.della_pink.Home.pertemuan_2.SecondActivity
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.Home.pertemuan_4.DashboardPanenActivity
import com.example.della_pink.Home.pertemuan_4.ProfilActivity
import com.example.della_pink.R
import com.example.della_pink.SettingActivity
import com.example.della_pink.adapter.NewsAdapter
import com.example.della_pink.databinding.FragmentHomeBinding
import com.example.della_pink.model.Article

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        try {
            binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNews.setHasFixedSize(true)
            loadBeritaDesaLokal()

            binding.btnRefreshNews.setOnClickListener {
                loadBeritaDesaLokal()
                Toast.makeText(requireContext(), "Berita diperbarui!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("HomeError", "Masalah pada RecyclerView Berita: ${e.message}")
        }

        try {
            setupMenuGridListeners()
        } catch (e: Exception) {
            Log.e("HomeError", "Masalah pada Tombol Grid Menu: ${e.message}")
        }
        try {
            binding.btnLogout.setOnClickListener {
                val pref = requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
                pref.edit().putBoolean("isLogin", false).apply() // Reset status login

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity?.finish()
            }
        } catch (e: Exception) {
            Log.e("HomeError", "Tombol logout bermasalah di XML: ${e.message}")
        }
    }

    private fun setupMenuGridListeners() {
        binding.btnRumus.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btnProfilPengguna.setOnClickListener {
            startActivity(Intent(requireContext(), ProfilActivity::class.java))
        }

        binding.btnPanen.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardPanenActivity::class.java))
        }

        binding.btnWebView.setOnClickListener {
            val webUrl = "https://perangkat.alwaysdata.net/"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)))
        }

        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        binding.btnSetting.setOnClickListener {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }
    }

    private fun loadBeritaDesaLokal() {
        try {
            val kumpulanTeksBerita = listOf(
                Article("Sistem Administrasi Desa Berbasis Digital Sukses Diluncurkan", "Penerapan aplikasi Bina Desa kini resmi beroperasi.", null),
                Article("Peningkatan Sektor Komoditas Pertanian Desa Bulan Ini", "Volume panen padi dan jagung menyusul perbaikan irigasi.", null),
                Article("Musyawarah Perangkat Desa Bahas Alokasi Dana Fasilitas Umum", "Rapat pleno menyepakati percepatan jalan utama.", null)
            )
            binding.rvNews.adapter = NewsAdapter(kumpulanTeksBerita)
        } catch (e: Exception) {
            Log.e("HomeError", "Gagal set adapter berita: ${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
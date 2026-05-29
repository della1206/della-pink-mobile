package com.example.della_pink.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.setHasFixedSize(true)
        loadBeritaDesaLokal()
        binding.btnRefreshNews.setOnClickListener {
            loadBeritaDesaLokal()
            Toast.makeText(requireContext(), "Berita diperbarui!", Toast.LENGTH_SHORT).show()
        }
        setupMenuGridListeners()
        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }
    }
    private fun setupMenuGridListeners() {
        binding.btnRumus.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), "Membuka Menu Rumus", Toast.LENGTH_SHORT).show()
        }

        binding.btnProfilPengguna.setOnClickListener {
            val intent = Intent(requireContext(), ProfilActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), "Membuka Profil Pengguna", Toast.LENGTH_SHORT).show()
        }

        binding.btnPanen.setOnClickListener {
            val intent = Intent(requireContext(), DashboardPanenActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), "Membuka Data Panen", Toast.LENGTH_SHORT).show()
        }

        binding.btnWebView.setOnClickListener {
            val webUrl = "https://perangkat.alwaysdata.net/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(intent)
        }
        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), "Membuka Pertemuan 10", Toast.LENGTH_SHORT).show()
        }

        binding.btnSetting.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), "Membuka Tentang Aplikasi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadBeritaDesaLokal() {
        val kumpulanTeksBerita = listOf(
            Article(
                title = "Berita Masa Kini: Sistem Administrasi Desa Berbasis Digital Sukses Diluncurkan",
                description = "Penerapan aplikasi Bina Desa kini resmi beroperasi guna membantu efisiensi pelayanan surat-menyurat warga secara mandiri.",
                urlToImage = null // Dibuat null karena gambar tidak diperlukan lagi
            ),
            Article(
                title = "Peningkatan Sektor Komoditas Pertanian Desa Bulan Ini",
                description = "Laporan tim pendamping lapangan mencatat adanya kenaikan drastis pada volume panen padi dan jagung menyusul perbaikan sistem irigasi.",
                urlToImage = null
            ),
            Article(
                title = "Musyawarah Perangkat Desa Bahas Alokasi Dana Fasilitas Umum",
                description = "Rapat pleno perangkat desa menyepakati pengalihan dana operasional untuk percepatan perbaikan akses infrastruktur jalan utama.",
                urlToImage = null
            ),
            Article(
                title = "Pelatihan Pemanfaatan Web Desa untuk Kelompok UMKM",
                description = "Guna memperluas jangkauan pasar produk lokal, perangkat desa menggelar sosialisasi intensif cara berjualan lewat portal internet resmi desa.",
                urlToImage = null
            )
        )

        val teksBeritaAcak = kumpulanTeksBerita.shuffled()
        binding.rvNews.adapter = NewsAdapter(teksBeritaAcak)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
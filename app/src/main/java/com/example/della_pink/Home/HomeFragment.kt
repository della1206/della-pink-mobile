package com.example.della_pink.Home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.della_pink.Home.pertemuan_10.TenthActivity
import com.example.della_pink.Home.pertemuan_13.ThirteenthActivity
import com.example.della_pink.Home.pertemuan_2.SecondActivity
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.Home.pertemuan_4.DashboardPanenActivity
import com.example.della_pink.Home.pertemuan_4.ProfilActivity
import com.example.della_pink.R
import com.example.della_pink.SettingActivity
import com.example.della_pink.adapter.NewsAdapter
import com.example.della_pink.databinding.FragmentHomeBinding
import com.example.della_pink.model.Article
import com.example.della_pink.notification.NotificationDetailActivity
import com.example.della_pink.utils.NotificationHelper
import com.example.della_pink.utils.PermissionHelper
import com.example.della_pink.utils.ReminderHelper
import java.util.Calendar

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Launcher untuk permission notifikasi (SESUAI MODUL)
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "✅ Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "❌ Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        // 🔔 REQUEST PERMISSION NOTIFIKASI (SESUAI MODUL)
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // 📰 Setup Berita
        setupBerita()

        // 📌 Setup Menu Grid
        setupMenuGridListeners()

        // 🔔 Setup Notifikasi & Reminder
        setupNotificationAndReminder()

        // 🚪 Setup Logout
        setupLogout()
    }

    /**
     * Setup Berita Desa
     */
    private fun setupBerita() {
        try {
            binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNews.setHasFixedSize(true)
            loadBeritaDesaLokal()

            binding.btnRefreshNews.setOnClickListener {
                loadBeritaDesaLokal()

                // 🔔 NOTIFIKASI SAAT REFRESH BERITA (SEPERTI MODUL)
                val intent = Intent(requireContext(), NotificationDetailActivity::class.java)
                NotificationHelper.showNotification(
                    requireContext(),
                    "📰 Berita Bina Desa",
                    "Berita desa berhasil diperbarui!",
                    intent
                )

                Toast.makeText(requireContext(), "✅ Berita diperbarui!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("HomeError", "Masalah pada RecyclerView Berita: ${e.message}")
        }
    }

    /**
     * Setup Notifikasi & Reminder di Halaman Utama
     */
    private fun setupNotificationAndReminder() {
        // 📨 TOMBOL KIRIM NOTIFIKASI
        binding.btnKirimNotifikasi.setOnClickListener {
            val namaWarga = binding.inputNamaWarga.text.toString().trim()
            val namaTampil = if (namaWarga.isEmpty()) "Warga Desa" else namaWarga

            val intent = Intent(requireContext(), NotificationDetailActivity::class.java)

            // NOTIFIKASI SEPERTI DI MODUL - dengan tema Bina Desa
            NotificationHelper.showNotification(
                requireContext(),
                "🏡 Informasi Bina Desa",
                "Halo $namaTampil, Selamat datang di aplikasi Bina Desa!",
                intent
            )

            Toast.makeText(requireContext(), "📨 Notifikasi dikirim untuk $namaTampil!", Toast.LENGTH_SHORT).show()
        }

        // ⏰ TOMBOL REMINDER
        binding.btnReminder.setOnClickListener {
            val namaWarga = binding.inputNamaWarga.text.toString().trim()
            val namaTampil = if (namaWarga.isEmpty()) "Warga Desa" else namaWarga

            // Ambil durasi dari chip yang dipilih
            val durationMinutes = getSelectedDuration()

            // Set reminder sesuai durasi yang dipilih
            setReminderWithDuration(
                minutes = durationMinutes,
                nama = namaTampil
            )
        }
    }

    /**
     * Ambil durasi dari chip yang dipilih
     */
    private fun getSelectedDuration(): Int {
        return when {
            binding.chip1Menit.isChecked -> 1
            binding.chip5Menit.isChecked -> 5
            binding.chip10Menit.isChecked -> 10
            binding.chip30Menit.isChecked -> 30
            else -> 1 // Default 1 menit
        }
    }

    /**
     * Set reminder dengan durasi tertentu
     */
    private fun setReminderWithDuration(minutes: Int, nama: String) {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, minutes)
        }

        val judul = when (minutes) {
            1 -> "⏰ Pengingat 1 Menit"
            5 -> "⏰ Pengingat 5 Menit"
            10 -> "⏰ Pengingat 10 Menit"
            30 -> "⏰ Pengingat 30 Menit"
            else -> "⏰ Pengingat $minutes Menit"
        }

        val pesan = when (minutes) {
            1 -> "Halo $nama, reminder ini muncul 1 menit setelah tombol ditekan"
            5 -> "Halo $nama, jangan lewatkan kegiatan desa! (5 menit)"
            10 -> "Halo $nama, ada informasi baru di Bina Desa! (10 menit)"
            30 -> "Halo $nama, persiapan kegiatan desa! (30 menit)"
            else -> "Halo $nama, reminder Bina Desa!"
        }

        ReminderHelper.setReminder(
            context = requireContext(),
            hour = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            title = judul,
            message = pesan,
            targetActivity = NotificationDetailActivity::class.java
        )

        Toast.makeText(
            requireContext(),
            "⏰ Reminder akan muncul dalam $minutes menit untuk $nama!",
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Setup Menu Grid
     */
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

        // ✅ TAMBAHKAN INI - TOMBOL PERTEMUAN 13
        binding.btnPertemuan13.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }
    }

    /**
     * Load Berita Desa Lokal
     */
    private fun loadBeritaDesaLokal() {
        try {
            val kumpulanTeksBerita = listOf(
                Article(
                    "🌾 Sistem Administrasi Desa Berbasis Digital Sukses Diluncurkan",
                    "Penerapan aplikasi Bina Desa kini resmi beroperasi untuk memudahkan pelayanan warga.",
                    null
                ),
                Article(
                    "🌽 Peningkatan Sektor Komoditas Pertanian Desa Bulan Ini",
                    "Volume panen padi dan jagung meningkat 20% menyusul perbaikan sistem irigasi.",
                    null
                ),
                Article(
                    "🏛️ Musyawarah Perangkat Desa Bahas Alokasi Dana Fasilitas Umum",
                    "Rapat pleno menyepakati percepatan pembangunan jalan utama desa.",
                    null
                )
            )
            binding.rvNews.adapter = NewsAdapter(kumpulanTeksBerita)
        } catch (e: Exception) {
            Log.e("HomeError", "Gagal set adapter berita: ${e.message}")
        }
    }

    /**
     * Setup Logout
     */
    private fun setupLogout() {
        try {
            binding.btnLogout.setOnClickListener {
                val pref = requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
                pref.edit().putBoolean("isLogin", false).apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity?.finish()
            }
        } catch (e: Exception) {
            Log.e("HomeError", "Tombol logout bermasalah: ${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
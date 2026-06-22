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
import com.example.della_pink.Home.pertemuan_7.ListViewActivity
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import kotlin.random.Random

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        setupBerita()
        setupMenuGridListeners()
        setupNotificationAndReminder()
        setupLogout()
        setupChipGroup()
        setupSearch()
    }

    private fun setupBerita() {
        try {
            binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNews.setHasFixedSize(true)
            loadBeritaDesaLokal()

            binding.btnRefreshNews.setOnClickListener {
                loadBeritaDesaLokal()

                val intent = Intent(requireContext(), NotificationDetailActivity::class.java)
                NotificationHelper.showNotification(
                    requireContext(),
                    "📰 Berita Bina Desa",
                    "Berita desa berhasil diperbarui!",
                    intent
                )

                Snackbar.make(
                    binding.root,
                    "✅ Berita berhasil diperbarui!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            Log.e("HomeError", "Masalah pada RecyclerView Berita: ${e.message}")
        }
    }

    private fun setupNotificationAndReminder() {
        binding.btnKirimNotifikasi.setOnClickListener {
            val namaWarga = binding.inputNamaWarga.text.toString().trim()
            val namaTampil = if (namaWarga.isEmpty()) "Warga Desa" else namaWarga

            val intent = Intent(requireContext(), NotificationDetailActivity::class.java)

            NotificationHelper.showNotification(
                requireContext(),
                "🏡 Informasi Bina Desa",
                "Halo $namaTampil, Selamat datang di aplikasi Bina Desa!",
                intent
            )

            Snackbar.make(
                binding.root,
                "📨 Notifikasi dikirim untuk $namaTampil!",
                Snackbar.LENGTH_SHORT
            ).show()

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("✅ Notifikasi Terkirim!")
                .setMessage("Notifikasi berhasil dikirim ke $namaTampil")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.btnReminder.setOnClickListener {
            val namaWarga = binding.inputNamaWarga.text.toString().trim()
            val namaTampil = if (namaWarga.isEmpty()) "Warga Desa" else namaWarga

            val durationMinutes = getSelectedDuration()
            setReminderWithDuration(
                minutes = durationMinutes,
                nama = namaTampil
            )
        }
    }

    private fun getSelectedDuration(): Int {
        return when {
            binding.chip1Menit.isChecked -> 1
            binding.chip5Menit.isChecked -> 5
            binding.chip10Menit.isChecked -> 10
            binding.chip30Menit.isChecked -> 30
            else -> 1
        }
    }

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

        Snackbar.make(
            binding.root,
            "⏰ Reminder akan muncul dalam $minutes menit untuk $nama!",
            Snackbar.LENGTH_LONG
        ).show()

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("⏰ Reminder Diset!")
            .setMessage("Pengingat akan muncul dalam $minutes menit untuk $nama")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
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

        binding.btnPertemuan13.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }

        // ✅ TAMBAHAN PERTEMUAN 7
        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(requireContext(), ListViewActivity::class.java))
        }
    }

    private fun setupChipGroup() {
        binding.chipGroupKategori.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chipPertanian -> {
                    Snackbar.make(
                        binding.root,
                        "🔍 Filter: Pertanian",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                R.id.chipPengumuman -> {
                    Snackbar.make(
                        binding.root,
                        "🔍 Filter: Pengumuman",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupSearch() {
        binding.etCari.setOnEditorActionListener { _, _, _ ->
            val query = binding.etCari.text.toString().trim()
            if (query.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    "🔍 Mencari: $query",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            true
        }
    }

    private fun loadBeritaDesaLokal() {
        try {
            // ✅ BUAT VARIASI BERITA (akan berganti setiap refresh)
            val daftarBerita = listOf(
                listOf(
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
                ),
                listOf(
                    Article(
                        "🚜 Inovasi Pertanian Modern Mulai Diterapkan",
                        "Petani desa mulai beralih ke sistem pertanian berbasis teknologi dan irigasi tetes.",
                        null
                    ),
                    Article(
                        "📊 Data Kependudukan Desa Berhasil Didigitalisasi",
                        "Seluruh data warga desa kini tersimpan dalam sistem digital yang aman.",
                        null
                    ),
                    Article(
                        "🌿 Program Penghijauan Desa Dilanjutkan",
                        "Penanaman 1000 pohon di area desa dimulai pekan depan.",
                        null
                    )
                ),
                listOf(
                    Article(
                        "🎉 Festival Budaya Desa Akan Digelar",
                        "Acara tahunan budaya desa akan digelar pada akhir bulan ini.",
                        null
                    ),
                    Article(
                        "🏥 Pos Kesehatan Desa Dibuka Setiap Hari",
                        "Pelayanan kesehatan gratis bagi warga desa kini tersedia setiap hari.",
                        null
                    ),
                    Article(
                        "📚 Pelatihan UMKM Digelar untuk Warga",
                        "Pelatihan kewirausahaan bagi pelaku UMKM desa akan dimulai minggu depan.",
                        null
                    )
                ),
                listOf(
                    Article(
                        "🌱 Panen Raya Padi Dimulai",
                        "Petani desa mulai panen raya dengan hasil melimpah tahun ini.",
                        null
                    ),
                    Article(
                        "🏗️ Pembangunan Jalan Desa Selesai",
                        "Proyek pembangunan jalan utama desa telah selesai tepat waktu.",
                        null
                    ),
                    Article(
                        "📱 Aplikasi Bina Desa Update Versi 2.0",
                        "Aplikasi Bina Desa hadir dengan fitur-fitur baru yang lebih canggih.",
                        null
                    )
                )
            )

            // ✅ Pilih berita secara acak
            val randomIndex = Random.nextInt(daftarBerita.size)
            val kumpulanTeksBerita = daftarBerita[randomIndex]

            binding.rvNews.adapter = NewsAdapter(kumpulanTeksBerita)

        } catch (e: Exception) {
            Log.e("HomeError", "Gagal set adapter berita: ${e.message}")
        }
    }

    private fun setupLogout() {
        try {
            binding.btnLogout.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("⚠️ Konfirmasi Keluar")
                    .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                    .setPositiveButton("Ya, Keluar") { dialog, _ ->
                        dialog.dismiss()
                        val pref = requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
                        pref.edit().putBoolean("isLogin", false).apply()

                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        activity?.finish()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
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
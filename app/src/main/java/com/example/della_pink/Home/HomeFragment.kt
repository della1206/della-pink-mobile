package com.example.della_pink.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.della_pink.Home.pertemuan_2.SecondActivity
import com.example.della_pink.Home.pertemuan_3.LoginActivity
import com.example.della_pink.Home.pertemuan_4.DashboardPanenActivity
import com.example.della_pink.Home.pertemuan_4.ProfilActivity
import com.example.della_pink.Home.pertemuan_10.TenthActivity // IMPORT TENTHACTIVITY
import com.example.della_pink.SettingActivity
import com.example.della_pink.WebViewActivity
import com.example.della_pink.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupChipGroup()
        setupButtonListeners()
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "Home Bina Desa"
        }
    }

    private fun setupChipGroup() {
        binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->
            checkedIds.firstOrNull()?.let { id ->
                val chip = group.findViewById<Chip>(id)
                Toast.makeText(requireContext(), "Kategori: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupButtonListeners() {
        with(binding) {
            btnRumus.setOnClickListener {
                startActivity(Intent(requireContext(), SecondActivity::class.java))
            }
            btnProfilPengguna.setOnClickListener {
                startActivity(Intent(requireContext(), ProfilActivity::class.java))
            }
            btnPanen.setOnClickListener {
                startActivity(Intent(requireContext(), DashboardPanenActivity::class.java))
            }
            btnWebView.setOnClickListener {
                val intent = Intent(requireContext(), WebViewActivity::class.java).apply {
                    putExtra("url", "https://perangkat.alwaysdata.net/")
                }
                startActivity(intent)
            }
            btnSetting.setOnClickListener {
                startActivity(Intent(requireContext(), SettingActivity::class.java))
            }

            // TAMBAHKAN LINK BUTTON PERTEMUAN 10 DI SINI
            btnPertemuan10.setOnClickListener {
                startActivity(Intent(requireContext(), TenthActivity::class.java))
            }

            btnLogout.setOnClickListener {
                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
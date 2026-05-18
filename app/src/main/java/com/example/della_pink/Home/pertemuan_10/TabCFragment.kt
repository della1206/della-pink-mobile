package com.example.della_pink.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.della_pink.databinding.FragmentTabCBinding
import com.example.della_pink.Home.pertemuan_10.ProductModel
import com.example.della_pink.Home.pertemuan_10.ProductAdapter

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    // PERBAIKAN: Menggunakan URL gambar foto orang asli Indonesia yang di-host secara aman
    private val PerangkatDesaList = listOf(
        // -- Pemerintah Desa --
        ProductModel("H. Ahmad Subarjo", "Kepala Desa (Kades)", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80"), // Pria Resmi
        ProductModel("Siti Aminah, S.An", "Sekretaris Desa (Sekdes)", "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=400&auto=format&fit=crop&q=80"), // Wanita Kantoran
        ProductModel("Budi Setiawan", "Kaur Keuangan", "https://kertosari-pasrujambe.lumajangkab.go.id/assets/files/galeri/sedang_Faizin%203x4%20B.jpg"), // Pria Asia
        ProductModel("Dewi Lestari", "Kaur Perencanaan", "https://images.unsplash.com/photo-1567532939604-b6b5b0db2604?w=400&auto=format&fit=crop&q=80"), // Wanita Asia
        ProductModel("Hendro Wibowo", "Kasi Pemerintahan", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&auto=format&fit=crop&q=80"), // Pria Senyum


        ProductModel("Ir. Supriyadi", "Ketua BPD", "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=400&auto=format&fit=crop&q=80"), // Pria Dewasa
        ProductModel("Hj. Rahmawati", "Ketua TP-PKK Desa", "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=400&auto=format&fit=crop&q=80"), // Wanita Kacamata
        ProductModel("dr. Joko Susilo", "Ketua LPMD", "https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?w=400&auto=format&fit=crop&q=80"), // Pria Profesional
        ProductModel("Andi Wijaya", "Ketua Karang Taruna", "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=400&auto=format&fit=crop&q=80"), // Pria Muda


        ProductModel("Rahmat Hidayat", "Ketua RW 01", "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=400&auto=format&fit=crop&q=80"), // Pria Kasual
        ProductModel("Siti Fatimah", "Ketua RW 02", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80") // Wanita Kasual
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(PerangkatDesaList) { perangkat ->
            Toast.makeText(
                requireContext(),
                "Melihat Profil: ${perangkat.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.della_pink.Profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.della_pink.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Developer Profile"

        binding.btnGithub.setOnClickListener {
            openUrl("https://github.com/della1206/della-pink-mobile")
        }

        binding.btnLinkedin.setOnClickListener {
            openUrl("https://www.linkedin.com/in/della-marcelina-433a003b1?utm_source=share_via&utm_content=profile&utm_medium=member_android")
        }

        binding.btnInstagram.setOnClickListener {
            openUrl("https://www.instagram.com/rclnaaa_?igsh=MW9jMXU5ZTNqZW9tNQ==")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

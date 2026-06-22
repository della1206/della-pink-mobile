package com.example.della_pink.Home.pertemuan_13

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.della_pink.databinding.FragmentTabQrcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

class TabQrcodeFragment : Fragment() {

    private var _binding: FragmentTabQrcodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabQrcodeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.btnGenerate.setOnClickListener {
            val text = binding.edtQrInput.text.toString().trim()
            if (text.isNotEmpty()) {
                try {
                    binding.ivQrCode.setImageBitmap(createQR(text))
                    Toast.makeText(requireContext(), "✅ QR Code berhasil dibuat", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "❌ Gagal membuat QR: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "❌ Masukkan teks terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createQR(text: String): Bitmap {
        val matrix: BitMatrix = QRCodeWriter()
            .encode(
                text,
                BarcodeFormat.QR_CODE,
                500,
                500
            )

        val bitmap = Bitmap.createBitmap(
            500,
            500,
            Bitmap.Config.RGB_565
        )

        for (x in 0 until 500) {
            for (y in 0 until 500) {
                bitmap.setPixel(
                    x,
                    y,
                    if (matrix[x, y])
                        Color.BLACK
                    else
                        Color.WHITE
                )
            }
        }

        return bitmap
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
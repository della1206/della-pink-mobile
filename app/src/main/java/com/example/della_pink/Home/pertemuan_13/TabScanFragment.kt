package com.example.della_pink.Home.pertemuan_13

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.della_pink.databinding.FragmentTabScanBinding
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

class TabScanFragment : Fragment() {

    private var _binding: FragmentTabScanBinding? = null
    private val binding get() = _binding!!
    private val executor = Executors.newSingleThreadExecutor()
    private val CAMERA_PERMISSION_CODE = 101

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabScanBinding.inflate(
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
        super.onViewCreated(view, savedInstanceState)

        // ✅ CEK PERMISSION KAMERA
        if (hasCameraPermission()) {
            startCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Izin kamera diperlukan untuk scan QR",
                    Toast.LENGTH_LONG
                ).show()
                binding.tvScanResult.text = "❌ Izin kamera ditolak"
                binding.tvScanResult.setTextColor(Color.RED)
            }
        }
    }

    private fun startCamera() {
        try {
            val future = ProcessCameraProvider.getInstance(requireContext())

            future.addListener({
                try {
                    val provider = future.get()

                    val preview = Preview.Builder()
                        .build()
                    preview.setSurfaceProvider(
                        binding.previewView.surfaceProvider
                    )

                    val scanner = BarcodeScanning.getClient(
                        BarcodeScannerOptions.Builder()
                            .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE,
                                Barcode.FORMAT_CODE_128,
                                Barcode.FORMAT_CODE_39
                            )
                            .build()
                    )

                    val analyzer = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()

                    analyzer.setAnalyzer(executor) { image ->
                        val media = image.image
                        if (media != null) {
                            scanner.process(
                                InputImage.fromMediaImage(
                                    media,
                                    image.imageInfo.rotationDegrees
                                )
                            )
                                .addOnSuccessListener { barcodes ->
                                    if (barcodes.isNotEmpty()) {
                                        val result = barcodes[0].rawValue ?: "QR terdeteksi"
                                        activity?.runOnUiThread {
                                            binding.tvScanResult.text = "✅ $result"
                                            binding.tvScanResult.setTextColor(Color.GREEN)
                                            Toast.makeText(
                                                requireContext(),
                                                "QR Terdeteksi!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                                .addOnCompleteListener {
                                    image.close()
                                }
                        }
                    }

                    provider.unbindAll()
                    provider.bindToLifecycle(
                        this,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        analyzer
                    )

                    activity?.runOnUiThread {
                        binding.tvScanResult.text = "📷 Arahkan QR Code ke tengah layar"
                        binding.tvScanResult.setTextColor(Color.WHITE)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    activity?.runOnUiThread {
                        binding.tvScanResult.text = "❌ Error: ${e.message}"
                        binding.tvScanResult.setTextColor(Color.RED)
                    }
                }
            }, ContextCompat.getMainExecutor(requireContext()))

        } catch (e: Exception) {
            e.printStackTrace()
            activity?.runOnUiThread {
                binding.tvScanResult.text = "❌ Error: ${e.message}"
                binding.tvScanResult.setTextColor(Color.RED)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        executor.shutdown()
    }
}
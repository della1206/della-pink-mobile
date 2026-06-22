package com.example.della_pink.Home.pertemuan_13

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.della_pink.databinding.FragmentTabCaptureBinding

class TabCaptureFragment : Fragment() {

    private var _binding: FragmentTabCaptureBinding? = null
    private val binding get() = _binding!!
    private val CAMERA_PERMISSION_CODE = 102

    private var currentPhotoUri: Uri? = null

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                currentPhotoUri?.let {
                    binding.ivCapturedImage.setImageURI(it)
                    context?.sendBroadcast(
                        Intent(
                            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            it
                        )
                    )
                    Toast.makeText(context, "✅ Foto berhasil disimpan", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "❌ Gagal mengambil foto", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCaptureBinding.inflate(
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
        binding.btnCapture.setOnClickListener {
            if (hasCameraPermission())
                openCamera()
            else
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
                openCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Izin kamera diperlukan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        currentPhotoUri = createGalleryPhotoUri()
        intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        cameraLauncher.launch(intent)
    }

    private fun createGalleryPhotoUri(): Uri {
        val values = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "IMG_${System.currentTimeMillis()}.jpg"
            )
            put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/jpeg"
            )
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                "Pictures/TestCaptures"
            )
        }

        return requireContext()
            .contentResolver
            .insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.della_pink.Home.pertemuan_7

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.della_pink.databinding.ActivityListviewBinding

class ListViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ SETUP TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setupArrayAdapter()
        setupSimpleAdapter()
        setupCustomAdapter()
    }

    // 1️⃣ ArrayAdapter
    private fun setupArrayAdapter() {
        val dataArray = arrayOf(
            "Pertanian", "Peternakan", "Perikanan",
            "Perkebunan", "Kehutanan", "Perdagangan",
            "Industri", "Jasa", "Pariwisata"
        )

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dataArray
        )

        binding.listViewArray.adapter = arrayAdapter

        binding.listViewArray.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                "📌 ArrayAdapter: ${dataArray[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 2️⃣ SimpleAdapter
    private fun setupSimpleAdapter() {
        val dataSimple = listOf(
            mapOf("nama" to "🌾 Padi", "deskripsi" to "Komoditas Unggulan"),
            mapOf("nama" to "🌽 Jagung", "deskripsi" to "Pakan Ternak"),
            mapOf("nama" to "🫘 Kedelai", "deskripsi" to "Bahan Baku Tahu"),
            mapOf("nama" to "🌶️ Cabai", "deskripsi" to "Sayuran Pedas"),
            mapOf("nama" to "🧅 Bawang", "deskripsi" to "Bumbu Dapur")
        )

        val simpleAdapter = SimpleAdapter(
            this,
            dataSimple,
            android.R.layout.simple_list_item_2,
            arrayOf("nama", "deskripsi"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewSimple.adapter = simpleAdapter

        binding.listViewSimple.setOnItemClickListener { _, _, position, _ ->
            val item = dataSimple[position]
            Toast.makeText(
                this,
                "📌 SimpleAdapter: ${item["nama"]} - ${item["deskripsi"]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 3️⃣ CustomAdapter
    private fun setupCustomAdapter() {
        val dataCustom = listOf(
            CustomItem("🌾 Padi", "Komoditas Unggulan", "https://picsum.photos/200"),
            CustomItem("🌽 Jagung", "Pakan Ternak", "https://picsum.photos/201"),
            CustomItem("🫘 Kedelai", "Bahan Baku Tahu", "https://picsum.photos/202"),
            CustomItem("🌶️ Cabai", "Sayuran Pedas", "https://picsum.photos/203"),
            CustomItem("🧅 Bawang", "Bumbu Dapur", "https://picsum.photos/204")
        )

        val customAdapter = CustomAdapter(this, dataCustom)
        binding.listViewCustom.adapter = customAdapter

        binding.listViewCustom.setOnItemClickListener { _, _, position, _ ->
            val item = dataCustom[position]
            Toast.makeText(
                this,
                "📌 CustomAdapter: ${item.title} - ${item.subtitle}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
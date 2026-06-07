package com.example.della_pink.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warga")
data class WargaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nama: String,
    val nik: String,
    val jenisKelamin: String,
    val alamat: String
)
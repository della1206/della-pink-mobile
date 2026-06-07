package com.example.della_pink.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.della_pink.data.entity.WargaEntity

@Dao
interface WargaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWarga(warga: WargaEntity)

    @Update
    suspend fun updateWarga(warga: WargaEntity)

    @Delete
    suspend fun deleteWarga(warga: WargaEntity)

    @Query("SELECT * FROM warga ORDER BY id DESC")
    suspend fun getAllWarga(): List<WargaEntity>
}
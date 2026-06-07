package com.example.della_pink.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.della_pink.data.dao.NoteDao
import com.example.della_pink.data.dao.WargaDao // 👈 Mengarah ke interface query data warga desa
import com.example.della_pink.data.entity.NoteEntity
import com.example.della_pink.data.entity.WargaEntity

@Database(
    entities = [
        NoteEntity::class,
        WargaEntity::class
    ],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun wargaDao(): WargaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "perangkat_desa_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
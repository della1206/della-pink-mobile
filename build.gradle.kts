// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Gunakan format id string standar agar tidak memicu Unresolved Reference 'libs'
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false

    // FIX UTAMA: Daftarkan KSP dengan versi string yang cocok dengan Kotlin 1.9.10
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
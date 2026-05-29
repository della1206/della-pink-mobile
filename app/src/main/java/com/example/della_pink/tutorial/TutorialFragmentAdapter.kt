package com.example.della_pink.tutorial

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(
    activity: AppCompatActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {

    // Menentukan jumlah total halaman fragment tutorial (yaitu 3)
    override fun getItemCount(): Int {
        return fragments.size
    }

    // Membuat posisi fragment saat digeser sesuai indeksnya
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
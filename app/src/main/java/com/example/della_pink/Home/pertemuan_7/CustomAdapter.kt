package com.example.della_pink.Home.pertemuan_7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.della_pink.R

class CustomAdapter(
    private val context: Context,
    private val items: List<CustomItem>
) : BaseAdapter() {

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_custom_list, parent, false)

        val ivIcon = view.findViewById<ImageView>(R.id.ivIcon)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvSubtitle = view.findViewById<TextView>(R.id.tvSubtitle)

        val item = items[position]

        tvTitle.text = item.title
        tvSubtitle.text = item.subtitle

        Glide.with(context)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_menu)
            .error(R.drawable.ic_menu)
            .circleCrop()
            .into(ivIcon)

        return view
    }
}
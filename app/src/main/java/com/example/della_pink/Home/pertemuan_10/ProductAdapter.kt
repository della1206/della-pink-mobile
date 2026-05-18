package com.example.della_pink.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.della_pink.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder menggunakan ItemProductBinding dari item_product.xml yang baru saja kita perbaiki
    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            // tvProductName menampilkan Nama Lengkap Aparat/Ketua RT-RW
            tvProductName.text = item.name

            // tvProductPrice menampilkan Jabatan / Nama Lembaga (Warna Hijau Desa)
            tvProductPrice.text = item.price

            // Memuat gambar foto profil perangkat desa menggunakan library Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(android.R.color.darker_gray) // Gambar sementara saat loading
                .error(android.R.color.darker_gray)       // Gambar jika URL error
                .into(imgProduct)

            // Aksi klik pada kartu aparat desa
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}
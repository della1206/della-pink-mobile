package com.example.della_pink.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.della_pink.databinding.ItemNewsBinding
import com.example.della_pink.model.Article

class NewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.txtTitle.text = article.title
        holder.binding.txtDescription.text = article.description ?: "Tidak ada deskripsi."
    }
    override fun getItemCount(): Int = articles.size
}
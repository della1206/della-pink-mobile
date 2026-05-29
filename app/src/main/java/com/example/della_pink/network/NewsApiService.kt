package com.example.della_pink.network

import com.example.della_pink.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {

    @GET("dev-bina-desa/api-dummy/main/news.json")
    fun getTopNews(): Call<NewsResponse>
}
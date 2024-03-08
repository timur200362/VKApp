package com.example.vkapp.data.remote

import com.example.vkapp.data.remote.response.ProductsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products?limit=20")
    suspend fun loadProducts(): ProductsResponse
}
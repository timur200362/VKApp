package com.example.vkapp.data.remote

import com.example.vkapp.data.remote.response.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun loadProducts(@Query("skip") skip: Int, @Query("limit") limit: Int): ProductsResponse
}
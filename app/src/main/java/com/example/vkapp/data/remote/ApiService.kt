package com.example.vkapp.data.remote

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.remote.response.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun loadProducts(@Query("limit") limit: Int, @Query("skip") skip: Int): ProductsResponse

    @GET("products/search")
    suspend fun searchProduct(@Query("q") title: String): ProductsResponse
}
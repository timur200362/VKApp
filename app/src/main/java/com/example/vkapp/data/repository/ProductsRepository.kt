package com.example.vkapp.data.repository

import com.example.vkapp.data.memory.Product

interface ProductsRepository {
    suspend fun getProducts(limit: Int, skip: Int): List<Product>
    suspend fun searchProduct(title: String): List<Product>
}
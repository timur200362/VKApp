package com.example.vkapp.data.repository

import com.example.vkapp.data.memory.Product

interface GetProductsRepository {
    suspend fun getProducts(): List<Product>
}
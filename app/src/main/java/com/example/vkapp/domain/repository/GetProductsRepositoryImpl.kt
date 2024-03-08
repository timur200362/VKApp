package com.example.vkapp.domain.repository

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.remote.ApiService
import com.example.vkapp.data.repository.GetProductsRepository

class GetProductsRepositoryImpl(
    private val apiService: ApiService
):GetProductsRepository {
    override suspend fun getProducts(): List<Product> {
        return apiService.loadProducts().products
    }
}
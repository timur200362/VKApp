package com.example.vkapp.domain.repository

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.remote.ApiService
import com.example.vkapp.data.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val apiService: ApiService
):ProductsRepository {
    override suspend fun getProducts(limit: Int, skip: Int): List<Product> {
        return apiService.loadProducts(limit, skip).products
    }

    override suspend fun searchProduct(title: String): List<Product> {
        return apiService.searchProduct(title).products
    }
}
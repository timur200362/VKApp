package com.example.vkapp.domain.repository

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.remote.ApiService
import com.example.vkapp.data.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val apiService: ApiService
):ProductsRepository {
    override suspend fun getProducts(limit: Int, skip: Int): List<Product> {
        cachedListProducts = apiService.loadProducts(limit, skip).products
        return cachedListProducts
    }

    override suspend fun searchProduct(title: String): List<Product> {
        cachedListProducts = apiService.searchProduct(title).products
        return cachedListProducts
    }
    companion object {
        var cachedListProducts = listOf<Product>()
    }
}
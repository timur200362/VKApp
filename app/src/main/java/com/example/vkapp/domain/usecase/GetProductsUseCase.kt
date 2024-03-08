package com.example.vkapp.domain.usecase

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.repository.ProductsRepository

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend fun execute(limit: Int, skip: Int):List<Product>{
        return productsRepository.getProducts(limit, skip)
    }
}
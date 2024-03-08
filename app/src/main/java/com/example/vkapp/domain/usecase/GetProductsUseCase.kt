package com.example.vkapp.domain.usecase

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.repository.GetProductsRepository

class GetProductsUseCase(
    private val getProductsRepository: GetProductsRepository
) {
    suspend fun execute(limit: Int, skip: Int):List<Product>{
        return getProductsRepository.getProducts(limit, skip)
    }
}
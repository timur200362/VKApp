package com.example.vkapp.domain.usecase

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.repository.ProductsRepository

class SearchProductUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend fun execute(title:String):List<Product>{
        return productsRepository.searchProduct(title)
    }
}
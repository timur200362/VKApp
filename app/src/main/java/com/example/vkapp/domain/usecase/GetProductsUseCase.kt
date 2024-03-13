package com.example.vkapp.domain.usecase

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.repository.ProductsRepository
private const val limit = 20
class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend fun execute(page: Int):List<Product>{
        return productsRepository.getProducts(limit, limit*(page-1))
    }
}
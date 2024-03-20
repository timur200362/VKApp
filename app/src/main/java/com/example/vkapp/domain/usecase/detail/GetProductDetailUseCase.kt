package com.example.vkapp.domain.usecase.detail

import com.example.vkapp.data.memory.Product
import com.example.vkapp.data.repository.ProductsRepository

class GetProductDetailUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend fun execute(id:Int):Product{
        return productsRepository.getProductDetail(id)
    }
}
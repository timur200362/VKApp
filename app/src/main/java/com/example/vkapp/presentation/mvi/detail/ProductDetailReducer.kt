package com.example.vkapp.presentation.mvi.detail

import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.domain.usecase.SearchProductUseCase
import com.example.vkapp.domain.usecase.detail.GetProductDetailUseCase
import com.example.vkapp.mviRealisation.Reducer

class ProductDetailReducer(
    initial: ProductDetailScreenState,
    private val getProductDetailUseCase: GetProductDetailUseCase,
): Reducer<ProductDetailScreenState, ProductDetailScreenUiEvent>(initial) {

    override suspend fun reduce(
        currentState: ProductDetailScreenState,
        event: ProductDetailScreenUiEvent
    ) {
        when (event) {
            is ProductDetailScreenUiEvent.GetProduct -> {
                val product = getProductDetailUseCase.execute(event.id)
                setState(currentState.copy(product))
            }
        }
    }
}
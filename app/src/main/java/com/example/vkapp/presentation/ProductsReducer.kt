package com.example.vkapp.presentation

import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.mviRealisation.Reducer

class ProductsReducer(
    initial: ProductsScreenState,
    private val getProductsUseCase: GetProductsUseCase
): Reducer<ProductsScreenState, ProductsScreenUiEvent>(initial) {
    override suspend fun reduce(
        oldState: ProductsScreenState,
        event: ProductsScreenUiEvent) {
        when (event) {
            is ProductsScreenUiEvent.GetProducts -> {
                val productsList = getProductsUseCase.execute()
                setState(oldState.copy(productsList))//newState
            }
        }
    }
}
package com.example.vkapp.presentation.mvi

import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.mviRealisation.Reducer

class ProductsReducer(
    initial: ProductsScreenState,
    private val getProductsUseCase: GetProductsUseCase
): Reducer<ProductsScreenState, ProductsScreenUiEvent>(initial) {

    private var skip = 0
    private val limit = 20

    override suspend fun reduce(
        oldState: ProductsScreenState,
        event: ProductsScreenUiEvent
    ) {
        when (event) {
            is ProductsScreenUiEvent.GetProducts -> {
                val productsList = getProductsUseCase.execute(limit, skip)
                val updatedProductsList = if (skip == 0){
                    productsList
                } else {
                    oldState.productsList.toMutableList().apply { addAll(productsList) }
                }
                setState(oldState.copy(productsList = updatedProductsList))
                skip+=limit
            }
        }
    }
}
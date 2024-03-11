package com.example.vkapp.presentation.mvi

import com.example.vkapp.data.memory.Product
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.domain.usecase.SearchProductUseCase
import com.example.vkapp.mviRealisation.Reducer

class ProductsReducer(
    initial: ProductsScreenState,
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductUseCase: SearchProductUseCase
): Reducer<ProductsScreenState, ProductsScreenUiEvent>(initial) {

    override suspend fun reduce(
        oldState: ProductsScreenState,
        event: ProductsScreenUiEvent
    ) {
        when (event) {
            is ProductsScreenUiEvent.GetProducts -> {
                val productsList = getProductsUseCase.execute(event.limit, event.skip)
                setState(oldState.copy(
                    productsList = productsList,
                ))
            }
            is ProductsScreenUiEvent.SearchProduct -> {
                val foundProducts = searchProductUseCase.execute(event.title)
                setState(oldState.copy(foundProducts))
            }
        }
    }
}
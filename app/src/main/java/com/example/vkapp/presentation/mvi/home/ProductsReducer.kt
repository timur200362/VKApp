package com.example.vkapp.presentation.mvi.home

import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.domain.usecase.SearchProductUseCase
import com.example.vkapp.mviRealisation.Reducer

class ProductsReducer(
    initial: ProductsScreenState,
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductUseCase: SearchProductUseCase
): Reducer<ProductsScreenState, ProductsScreenUiEvent>(initial) {

    override suspend fun reduce(
        currentState: ProductsScreenState,
        event: ProductsScreenUiEvent
    ) {
        when (event) {
            is ProductsScreenUiEvent.GetProducts -> {
                val productsList = getProductsUseCase.execute(currentState.page)
                setState(currentState.copy(productsList = productsList))
            }
            is ProductsScreenUiEvent.SearchProduct -> {
                val foundProducts = searchProductUseCase.execute(event.title)
                setState(currentState.copy(foundProducts))
            }
            is ProductsScreenUiEvent.PreviousPage -> {
                setState(currentState.copy(page = currentState.page - 1))
                sendEvent(ProductsScreenUiEvent.GetProducts)
            }
            is ProductsScreenUiEvent.NextPage -> {
                setState(currentState.copy(page = currentState.page + 1))
                sendEvent(ProductsScreenUiEvent.GetProducts)
            }
        }
    }
}
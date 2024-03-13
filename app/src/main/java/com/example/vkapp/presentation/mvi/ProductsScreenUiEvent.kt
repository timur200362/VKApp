package com.example.vkapp.presentation.mvi

import com.example.vkapp.mviRealisation.UiEvent

sealed class ProductsScreenUiEvent : UiEvent {
    data object GetProducts : ProductsScreenUiEvent()
    data class SearchProduct(val title: String): ProductsScreenUiEvent()
    data object NextPage: ProductsScreenUiEvent()
    data object PreviousPage: ProductsScreenUiEvent()
}
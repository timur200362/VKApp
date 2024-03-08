package com.example.vkapp.presentation.mvi

import com.example.vkapp.mviRealisation.UiEvent

sealed class ProductsScreenUiEvent : UiEvent {
    data class GetProducts(val limit: Int, val skip: Int) : ProductsScreenUiEvent()
}
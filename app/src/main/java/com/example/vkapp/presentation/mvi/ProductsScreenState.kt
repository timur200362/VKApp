package com.example.vkapp.presentation.mvi

import com.example.vkapp.data.memory.Product
import com.example.vkapp.mviRealisation.UiState

data class ProductsScreenState(
    val productsList: List<Product>
) : UiState {
    companion object {
        fun initial() = ProductsScreenState(
            productsList = listOf()
        )
    }
}
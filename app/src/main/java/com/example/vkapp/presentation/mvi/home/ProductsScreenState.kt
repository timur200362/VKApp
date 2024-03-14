package com.example.vkapp.presentation.mvi.home

import com.example.vkapp.data.memory.Product
import com.example.vkapp.mviRealisation.UiState

data class ProductsScreenState(
    val productsList: List<Product>,
    val page: Int,
    val title: String
) : UiState {
    companion object {
        fun initial() = ProductsScreenState(
            productsList = listOf(),
            page = 1,
            title = ""
        )
    }
}
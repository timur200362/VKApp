package com.example.vkapp.presentation.mvi

import com.example.vkapp.data.memory.Product
import com.example.vkapp.mviRealisation.UiState

data class ProductsScreenState(
    val productsList: List<Product>,
    val product: Product,
    val page: Int,
    val title: String
) : UiState {
    companion object {
        fun initial() = ProductsScreenState(
            productsList = listOf(),
            page = 1,
            product = Product(
                "",
                "",
                "",
                0,
                listOf(""),
                0,
                "",
                ""
            ),
            title = ""
        )
    }
}
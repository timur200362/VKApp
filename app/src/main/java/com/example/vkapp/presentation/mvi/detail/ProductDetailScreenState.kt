package com.example.vkapp.presentation.mvi.detail

import com.example.vkapp.data.memory.Product
import com.example.vkapp.mviRealisation.UiState

data class ProductDetailScreenState(
    val product: Product?
) : UiState {
    companion object {
        fun initial() = ProductDetailScreenState(
            product = null
        )
    }
}
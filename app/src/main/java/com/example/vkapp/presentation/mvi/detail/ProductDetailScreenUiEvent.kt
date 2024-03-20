package com.example.vkapp.presentation.mvi.detail

import com.example.vkapp.mviRealisation.UiEvent

sealed class ProductDetailScreenUiEvent : UiEvent {
    data class GetProduct(val id: Int) : ProductDetailScreenUiEvent()
}
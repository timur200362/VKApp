package com.example.vkapp.presentation

import com.example.vkapp.mviRealisation.UiEvent

sealed class ProductsScreenUiEvent : UiEvent {
    data object GetProducts : ProductsScreenUiEvent()
}
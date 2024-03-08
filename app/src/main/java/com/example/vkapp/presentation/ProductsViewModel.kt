package com.example.vkapp.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.mviRealisation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    getProductsUseCase: GetProductsUseCase
): BaseViewModel<ProductsScreenState, ProductsScreenUiEvent>(){

    private val reducer: ProductsReducer = ProductsReducer(
        ProductsScreenState.initial(),
        getProductsUseCase
    )

    init {
        sendEvent(ProductsScreenUiEvent.GetProducts)
    }

    override val state: StateFlow<ProductsScreenState>
        get() = reducer.state

    private fun sendEvent(event: ProductsScreenUiEvent) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                reducer.sendEvent(event)
            }
            catch (throwable: Throwable){
                println("Произошла ошибка!")
                Log.e("ProductsViewModel","Ошибка: $throwable")
            }
        }
    }
}
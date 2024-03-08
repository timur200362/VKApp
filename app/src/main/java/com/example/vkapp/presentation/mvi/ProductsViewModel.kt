package com.example.vkapp.presentation.mvi

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.mviRealisation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    getProductsUseCase: GetProductsUseCase
): BaseViewModel<ProductsScreenState, ProductsScreenUiEvent>(){

    private val reducer: ProductsReducer = ProductsReducer(
        ProductsScreenState.initial(),
        getProductsUseCase
    )

    private var skip = 0
    private val limit = 20

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        sendEvent(ProductsScreenUiEvent.GetProducts(limit, skip))
    }

    override val state: StateFlow<ProductsScreenState>
        get() = reducer.state

    private fun sendEvent(event: ProductsScreenUiEvent) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                _loading.value = true
                reducer.sendEvent(event)
            }
            catch (throwable: Throwable){
                println("Произошла ошибка!")
                Log.e("ProductsViewModel","Ошибка: $throwable")
            }
            finally {
                _loading.value = false
            }
        }
    }
}
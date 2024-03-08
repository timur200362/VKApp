package com.example.vkapp.presentation.mvi

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.domain.usecase.SearchProductUseCase
import com.example.vkapp.mviRealisation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    getProductsUseCase: GetProductsUseCase,
    searchProductUseCase: SearchProductUseCase
): BaseViewModel<ProductsScreenState, ProductsScreenUiEvent>(){

    private val reducer: ProductsReducer = ProductsReducer(
        ProductsScreenState.initial(),
        getProductsUseCase,
        searchProductUseCase
    )

    private val limit = 20
    private val skip = 0
    private val page = 1
    val title = mutableStateOf("")

    init {
        getProducts(ProductsScreenUiEvent.GetProducts(limit, skip, page))
        searchProduct(ProductsScreenUiEvent.SearchProduct(title.toString()))
    }

    override val state: StateFlow<ProductsScreenState>
        get() = reducer.state

    fun getProducts(event: ProductsScreenUiEvent) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                reducer.sendEvent(event)
            }
            catch (throwable: Throwable){
                println("Произошла ошибка!")
                Log.e("ProductsViewModel - Get","Ошибка: $throwable")
            }
        }
    }
    fun searchProduct(event: ProductsScreenUiEvent){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                reducer.sendEvent(event)
            }
            catch (throwable: Throwable){
                println("Произошла ошибка!")
                Log.e("ProductsViewModel - Search","Ошибка: $throwable")
            }
        }
    }
}
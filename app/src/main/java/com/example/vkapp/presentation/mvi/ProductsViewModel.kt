package com.example.vkapp.presentation.mvi

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.domain.usecase.SearchProductUseCase
import com.example.vkapp.mviRealisation.BaseViewModel
import kotlinx.coroutines.Dispatchers
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

    val title = mutableStateOf("")

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
                Log.e("ProductsViewModel - Get","Ошибка: $throwable")
            }
        }
    }
    fun nextPage(){
        sendEvent(ProductsScreenUiEvent.NextPage)
    }
    fun previousPage(){
        sendEvent(ProductsScreenUiEvent.PreviousPage)
    }
    fun searchProduct(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                sendEvent(ProductsScreenUiEvent.SearchProduct(title))
            }
            catch (throwable: Throwable){
                println("Произошла ошибка!")
                Log.e("ProductsViewModel - Search","Ошибка: $throwable")
            }
        }
    }
}
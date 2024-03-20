package com.example.vkapp.presentation.mvi.detail

import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.detail.GetProductDetailUseCase
import com.example.vkapp.mviRealisation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProductDetailViewModel(
    getProductDetailUseCase: GetProductDetailUseCase,
): BaseViewModel<ProductDetailScreenState, ProductDetailScreenUiEvent>() {

    private val reducer: ProductDetailReducer = ProductDetailReducer(
        ProductDetailScreenState.initial(),
        getProductDetailUseCase
    )


    override val state: StateFlow<ProductDetailScreenState>
        get() = reducer.state


    fun getDetailInfo(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            reducer.sendEvent(ProductDetailScreenUiEvent.GetProduct(id))
        }
    }
}
package com.example.vkapp.di

import com.example.vkapp.data.remote.ApiFactory
import com.example.vkapp.data.remote.ApiService
import com.example.vkapp.data.repository.GetProductsRepository
import com.example.vkapp.domain.repository.GetProductsRepositoryImpl
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.presentation.mvi.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsListModule = module {
    single<ApiService> { ApiFactory().productsApi }
    single<GetProductsRepository> { GetProductsRepositoryImpl(get()) }
    factory<GetProductsUseCase> { GetProductsUseCase(get()) }
    viewModel<ProductsViewModel>{
        ProductsViewModel(
            get()
        )
    }
}
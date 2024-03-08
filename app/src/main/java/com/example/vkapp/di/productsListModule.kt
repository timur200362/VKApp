package com.example.vkapp.di

import com.example.vkapp.data.remote.ApiFactory
import com.example.vkapp.data.remote.ApiService
import com.example.vkapp.data.repository.ProductsRepository
import com.example.vkapp.domain.repository.ProductsRepositoryImpl
import com.example.vkapp.domain.usecase.GetProductsUseCase
import com.example.vkapp.domain.usecase.SearchProductUseCase
import com.example.vkapp.presentation.mvi.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsListModule = module {
    single<ApiService> { ApiFactory().productsApi }
    single<ProductsRepository> { ProductsRepositoryImpl(get()) }
    factory<GetProductsUseCase> { GetProductsUseCase(get()) }
    factory<SearchProductUseCase> { SearchProductUseCase(get()) }
    viewModel<ProductsViewModel>{
        ProductsViewModel(
            get(),
            get()
        )
    }
}
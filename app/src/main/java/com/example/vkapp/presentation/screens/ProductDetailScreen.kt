package com.example.vkapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.vkapp.presentation.mvi.ProductsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailScreen(productId: Int) {
    DisplayInfo(productId)
}

@Composable
fun DisplayInfo(
    productId: Int,
    viewModel: ProductsViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    val product = state.productsList.find { it.id == productId }

    if (product!=null){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = product.images[1],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = product.title,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Категория: ${product.category}",
                modifier = Modifier.padding(start = 10.dp,top = 20.dp),
                fontSize = 18.sp,
            )
            Text(
                text = "Производитель: ${product.brand}",
                modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                fontSize = 18.sp,
            )
            Text(
                text = "Цена: ${product.price}",
                color = Color(255, 140, 0),
                modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                fontSize = 18.sp
            )
            Text(
                text = "Описание",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = product.description,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 18.sp
            )
        }
    }
}
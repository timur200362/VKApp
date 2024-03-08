package com.example.vkapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.vkapp.presentation.mvi.ProductsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home",
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable("home"){
            ProductsListScreen (
                onNavigateToDetail = {productId ->
                    navController.navigate("detail/$productId")
                }
            )
        }
        composable(
            "detail/{productId}",
            arguments = listOf(navArgument("productId") {defaultValue = 0})
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("productId")?.let { ProductDetailScreen(it) }
        }
    }
}


@Composable
fun ProductsListScreen(
    viewModel: ProductsViewModel = koinViewModel(),
    onNavigateToDetail: (Int) -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(state.productsList) {product ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .clickable(onClick = { onNavigateToDetail(product.id) })
                    .fillMaxWidth()
            ){
                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 15.dp)
                )
                Column {
                    Text(
                        text = product.title,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = product.category,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp
                    )
                }
            }
            if (state.productsList.indexOf(product) == state.productsList.size - 1  && !loading) {
                Button(
                    onClick = { state.productsList }
                ) {
                    Text("Загрузить еще")
                }
            }
        }
    }
}
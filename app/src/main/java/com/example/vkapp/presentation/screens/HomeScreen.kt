package com.example.vkapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.example.vkapp.data.memory.Product
import com.example.vkapp.presentation.mvi.ProductsScreenUiEvent
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
    onNavigateToDetail: (Int) -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Text(
                text = "Поиск товаров",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
            TextField(
                value = viewModel.title.value,
                onValueChange = { viewModel.title.value = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clip(shape = RoundedCornerShape(14.dp)),
                placeholder = { Text(text ="Search...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.searchProduct(viewModel.title.value)
                })
            )
        }
        item {
            Text(
                text = "Список товаров",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
        items(state.productsList) {productsList ->
            Card (
                modifier = Modifier
                    .clickable(onClick = { onNavigateToDetail(productsList.id) })
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .shadow(5.dp)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    AsyncImage(
                        model = productsList.thumbnail,
                        contentDescription = null,
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp)
                            .padding(end = 15.dp)
                            .background(Color.White)
                    )
                    Column {
                        Text(
                            text = productsList.title,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = productsList.description,
                            color = Color.Gray,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
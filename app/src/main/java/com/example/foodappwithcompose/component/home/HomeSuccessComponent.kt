package com.example.foodappwithcompose.component.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.customlayouts.AppBar
import com.example.foodappwithcompose.customlayouts.CategoryItemRowLayout
import com.example.foodappwithcompose.customlayouts.MealItemRowLayout
import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealDetail
import com.example.foodappwithcompose.model.MealDetailResponse

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeSuccessComponent(
    randomMeal: MealDetailResponse,
    searchMeal: MealDetailResponse,
    category: CategoryResponse,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(text = "Home Page")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MealItemRowLayout(randomMeal.meals, Modifier, navHostController = navHostController)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MealItemRowLayout(searchMeal.meals, Modifier, navHostController = navHostController)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(category.categories) {
                    CategoryItemRowLayout(categoriesItem = it)
                }
            }
        }
    }
}

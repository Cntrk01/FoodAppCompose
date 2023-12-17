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
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.category.CategorySuccessComponent
import com.example.foodappwithcompose.customlayouts.AppBar
import com.example.foodappwithcompose.customlayouts.CategoryListLayout
import com.example.foodappwithcompose.customlayouts.SingleMealLayout
import com.example.foodappwithcompose.customlayouts.TitleLayout
import com.example.foodappwithcompose.model.CategoryResponse
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
        AppBar(isVisible = false,text = "Home",color=Color.Black)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TitleLayout(title = "What Would You Like Eat")
            SingleMealLayout(meals = randomMeal.meals, modifier = Modifier, navHostController = navHostController)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TitleLayout(title = "Popular Meal")
            SingleMealLayout(meals = searchMeal.meals, modifier = Modifier, navHostController = navHostController)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TitleLayout(title = "Category")
            CategoryListLayout(listAlignment = "Horizontal",
                categoryResponse = category,
                navHostController = navHostController)
        }
    }
}

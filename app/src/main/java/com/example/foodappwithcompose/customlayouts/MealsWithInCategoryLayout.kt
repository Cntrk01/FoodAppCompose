package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.util.ScreenState
import com.example.foodappwithcompose.model.Meals
import com.example.foodappwithcompose.model.MealsResponse

//Categori altındaki mealları listelediğim sayfa
@Composable
fun MealsWithInCategoryLayout(
    mealsResponse: MealsResponse,
    navHostController: NavHostController,
    categoryName: String
) {
    Column {
        AppBar(isVisible = true, text = categoryName, color = Color.Black) {
            navHostController.popBackStack()
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(mealsResponse.meals) {
                MealItemDesign(meals = it, navHostController = navHostController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealItemDesign(
    meals: Meals,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(200.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            navHostController.navigate(route = ScreenState.CategoryItemDetail.route + "/${meals.idMeal}")
        })
    {
        MealLayout(mealImage = meals.strMealThumb, mealName = meals.strMeal)
    }
}

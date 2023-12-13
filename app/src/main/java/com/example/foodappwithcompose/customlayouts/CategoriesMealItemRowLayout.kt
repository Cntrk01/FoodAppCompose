package com.example.foodappwithcompose.customlayouts

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
import androidx.compose.ui.unit.dp
import com.example.foodappwithcompose.model.Meals
import com.example.foodappwithcompose.model.MealsResponse

@Composable
fun CategoriesMealItemRowLayout(mealsResponse: MealsResponse){
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(mealsResponse.meals){
            MealItemDesign(meals = it)
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealItemDesign(meals: Meals){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(200.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            //navHostController.navigate(route = "category/"+categoriesItem.strCategory)
        })
    {
        ItemRow(meals.strMealThumb,meals.strMeal)
    }
}

package com.example.foodappwithcompose.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.foodappwithcompose.model.Category
import com.example.foodappwithcompose.model.MealDetail

@Composable
fun MealDetail(navController: NavController){
    val x=navController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
    println(x?.idMeal   +  x?.strInstructions)
}
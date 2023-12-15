package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.viewmodel.MealDetailWithIdViewModel

@Composable
fun DetailWithMealId(navHostController: NavHostController){
    //idsi geliyor
    val viewModel : MealDetailWithIdViewModel = viewModel()
}
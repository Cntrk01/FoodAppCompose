package com.example.foodappwithcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodappwithcompose.model.MealDetail
import com.example.foodappwithcompose.ui.Home
import com.example.foodappwithcompose.ui.MealDetail

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = ScreenState.Home.route){
        composable(route = ScreenState.Home.route){
            Home(navController = navController)
        }
        composable(route = ScreenState.Detail.route){
            navController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
            MealDetail(navController = navController)
        }
    }
}
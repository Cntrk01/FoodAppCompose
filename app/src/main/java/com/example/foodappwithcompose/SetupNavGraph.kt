package com.example.foodappwithcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.foodappwithcompose.model.MealDetail
import com.example.foodappwithcompose.ui.CategoryPage
import com.example.foodappwithcompose.ui.HomePage
import com.example.foodappwithcompose.ui.DetailPage

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = ScreenState.Home.route){
        composable(route = ScreenState.Home.route){
            HomePage(navController = navController)
        }
        composable(route = ScreenState.Detail.route){
            navController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
            DetailPage(navController = navController)
        }
        composable(route=ScreenState.Category.route, arguments = listOf(
            navArgument("categoryId"){
                type= NavType.StringType
            }
        )){
            val categoryId=it.arguments?.getString("categoryId").toString()
            CategoryPage(categoryId)
        }
    }
}
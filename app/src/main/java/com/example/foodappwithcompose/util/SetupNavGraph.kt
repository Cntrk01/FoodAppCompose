package com.example.foodappwithcompose.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodappwithcompose.bottomnavbar.Category
import com.example.foodappwithcompose.bottomnavbar.Home
import com.example.foodappwithcompose.bottomnavbar.Search
import com.example.foodappwithcompose.model.MealDetail
import com.example.foodappwithcompose.ui.MealsWithInCategory
import com.example.foodappwithcompose.ui.DetailPage
import com.example.foodappwithcompose.ui.DetailWithMealId

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenState.HomeBottomItem.route
    ) {
        composable(route = ScreenState.HomeBottomItem.route) {
            Home(
                navHostController = navHostController,
                onItemClickNavigate = { route, arguments ->
                    if (route == ScreenState.Detail.route) {
                        navHostController.navigate(route = route)
                    }
                    if (route == ScreenState.Category.route) {
                        navHostController.navigate(route = route + "/${arguments}")
                    }
                })
        }
        composable(route = ScreenState.Detail.route) {
            navHostController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
            DetailPage(
                navController = navHostController,
                backClick = {
                    navHostController.popBackStack()
                })
        }
        composable(route = ScreenState.Category.route + "/{categoryName}") {
//          val categoryId=it.arguments?.getString("categoryId").toString()
//          bu id ile geliyor fakat ben viewmodelinin içinde SavedStateHandle ile taşınan idyi alıp init içerisinde çalıştırarak dataları fetch ettim
            MealsWithInCategory(
                backClick = {
                    navHostController.popBackStack()
                },
                itemDetailClick = { route, arguments ->
                    navHostController.navigate(route = route + "/${arguments}")
                })
        }
        composable(route = ScreenState.CategoryItemDetail.route + "/{mealId}") {
            DetailWithMealId(backClick = {
                navHostController.popBackStack()
            })
        }
        composable(route = ScreenState.CategoryBottomItem.route) {
            Category(onItemClickCategory = { route, arguments ->
                if (route == ScreenState.Category.route) {
                    navHostController.navigate(route = route + "/${arguments}")
                }
            })
        }
        composable(route = ScreenState.SearchBottomItem.route) {
            Search(
                navHostController = navHostController,
                onItemClickNavigate = {
                    navHostController.navigate(route = it)
                })
        }
    }
}
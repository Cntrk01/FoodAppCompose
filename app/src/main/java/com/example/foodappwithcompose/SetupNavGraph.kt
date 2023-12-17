package com.example.foodappwithcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodappwithcompose.bottomnavbar.Category
import com.example.foodappwithcompose.bottomnavbar.Home
import com.example.foodappwithcompose.model.MealDetail
import com.example.foodappwithcompose.ui.CategoryPage
import com.example.foodappwithcompose.ui.DetailPage
import com.example.foodappwithcompose.ui.DetailWithMealId

@Composable
fun SetupNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = ScreenState.HomeBottomItem.route){
        composable(route = ScreenState.HomeBottomItem.route){
            Home(navHostController = navHostController)
        }
        //HomePageMealItemRow içinde yönlendirme
        composable(route = ScreenState.Detail.route){
            navHostController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
            DetailPage(navController = navHostController)
        }
        //CategoriesRowLayout içinde yönlendirme
        composable(route=ScreenState.Category.route+"/{categoryName}"){
//          val categoryId=it.arguments?.getString("categoryId").toString()
//          bu id ile geliyor fakat ben viewmodelinin içinde SavedStateHandle ile taşınan idyi alıp init içerisinde çalıştırarak dataları fetch ettim
            CategoryPage(navHostController=navHostController)
        }
        //CategoriesMealItemRowLayout içinde yönlendirme
        composable(route=ScreenState.CategoryItemDetail.route+"/{mealId}"){
            DetailWithMealId(navHostController = navHostController)
        }
        //Bottombarda category sayfasına gidiyor
        composable(route = ScreenState.CategoryBottomItem.route){
           Category(navHostController = navHostController)
        }
    }
}
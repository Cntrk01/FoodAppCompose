package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.home.ErrorComponent
import com.example.foodappwithcompose.component.home.LoadingComponent
import com.example.foodappwithcompose.customlayouts.CategoriesMealItemRowLayout
import com.example.foodappwithcompose.state.CategoryPageState
import com.example.foodappwithcompose.viewmodel.CategoryPageViewModel

@Composable
fun CategoryPage(navHostController: NavHostController){
    val viewModel : CategoryPageViewModel = viewModel()

    val state by viewModel.state
    when(state){
        is CategoryPageState.Loading-> LoadingComponent()
        is CategoryPageState.Error -> {
            val message = (state as CategoryPageState.Error).error
            ErrorComponent(message,onRefreshClicked ={
                viewModel.tryToGetMeal()
            })
        }
        is CategoryPageState.Success->{
            val data=(state as CategoryPageState.Success).mealDetailResponse
            CategoriesMealItemRowLayout(mealsResponse = data, navHostController = navHostController)
        }
    }
}
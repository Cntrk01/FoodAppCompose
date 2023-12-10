package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.foodappwithcompose.component.home.HomeErrorComponent
import com.example.foodappwithcompose.component.home.HomeLoadingComponent
import com.example.foodappwithcompose.component.home.HomeSuccessComponent
import com.example.foodappwithcompose.intent.HomeIntent
import com.example.foodappwithcompose.state.HomeMealState
import com.example.foodappwithcompose.viewmodel.HomeViewModel

@Composable
fun Home(homeViewModel: HomeViewModel,navController: NavController){

    val state by homeViewModel.state

    when(state){
        is HomeMealState.Loading-> HomeLoadingComponent()
        is HomeMealState.Success->{
            val randomMeal=(state as HomeMealState.Success).randomMeal
            val searchMeal=(state as HomeMealState.Success).searchMeal
            val category=(state as HomeMealState.Success).category
            HomeSuccessComponent(randomMeal,searchMeal,category){
                it.map {mealDetail->
                    navController.navigate(route = "detail_page/${mealDetail.idMeal}/${mealDetail.strMeal}/${mealDetail.strCategory}/${mealDetail.strArea}/${mealDetail.strInstructions}/${mealDetail.strYoutube}")
                }
            }
        }
        is HomeMealState.Error->{
            val message = (state as HomeMealState.Error).error
            HomeErrorComponent(message,onRefreshClicked ={
                homeViewModel.processIntent(HomeIntent.Loading)
            })
        }
    }

    LaunchedEffect(Unit){
        homeViewModel.processIntent(HomeIntent.Loading)
    }
}
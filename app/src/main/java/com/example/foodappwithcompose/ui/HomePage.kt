package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.home.HomeErrorComponent
import com.example.foodappwithcompose.component.home.HomeLoadingComponent
import com.example.foodappwithcompose.component.home.HomeSuccessComponent
import com.example.foodappwithcompose.intent.HomeIntent
import com.example.foodappwithcompose.state.HomeMealState
import com.example.foodappwithcompose.viewmodel.HomeViewModel

@Composable
fun HomePage(navController: NavHostController){
    val homeViewModel : HomeViewModel = viewModel()
    var firstTimeCheck=true
    val state by homeViewModel.state

    when(state){
        is HomeMealState.Loading-> HomeLoadingComponent()
        is HomeMealState.Success->{
            val randomMeal=(state as HomeMealState.Success).randomMeal
            val searchMeal=(state as HomeMealState.Success).searchMeal
            val category=(state as HomeMealState.Success).category
            firstTimeCheck=(state as HomeMealState.Success).firstTimeRun
            HomeSuccessComponent(randomMeal,searchMeal,category,navController)
        }
        is HomeMealState.Error->{
            val message = (state as HomeMealState.Error).error
            HomeErrorComponent(message,onRefreshClicked ={
                homeViewModel.processIntent(HomeIntent.Loading)
            })
        }
    }
    if (firstTimeCheck){
        LaunchedEffect(Unit){
            homeViewModel.processIntent(HomeIntent.Loading)
        }
    }

}
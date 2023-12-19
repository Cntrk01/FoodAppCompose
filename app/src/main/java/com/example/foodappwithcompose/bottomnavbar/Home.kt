package com.example.foodappwithcompose.bottomnavbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.ErrorComponent
import com.example.foodappwithcompose.component.LoadingComponent
import com.example.foodappwithcompose.component.home.HomeComponent
import com.example.foodappwithcompose.intent.HomeIntent
import com.example.foodappwithcompose.state.HomePageState
import com.example.foodappwithcompose.viewmodel.HomePageViewModel

@Composable
fun Home(navHostController: NavHostController) {
    val homePageViewModel: HomePageViewModel = viewModel()
    var firstTimeCheck = true
    val state by homePageViewModel.state

    when (state) {
        is HomePageState.Loading -> LoadingComponent()
        is HomePageState.Success -> {
            val randomMeal = (state as HomePageState.Success).randomMeal
            val searchMeal = (state as HomePageState.Success).searchMeal
            val category = (state as HomePageState.Success).category
            firstTimeCheck = (state as HomePageState.Success).firstTimeRun
            HomeComponent(
                randomMeal = randomMeal,
                searchMeal = searchMeal,
                category = category,
                navHostController = navHostController
            )
        }

        is HomePageState.Error -> {
            val message = (state as HomePageState.Error).error
            ErrorComponent(
                errorMessage = message,
                onRefreshClicked = {
                homePageViewModel.processIntent(HomeIntent.Loading)
            })
        }
    }
    if (firstTimeCheck) {
        LaunchedEffect(Unit) {
            homePageViewModel.processIntent(intent = HomeIntent.Loading)
        }
    }
}
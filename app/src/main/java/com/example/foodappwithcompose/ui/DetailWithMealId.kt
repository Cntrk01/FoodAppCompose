package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.home.ErrorComponent
import com.example.foodappwithcompose.component.home.LoadingComponent
import com.example.foodappwithcompose.customlayouts.MealDetailRow
import com.example.foodappwithcompose.state.DetailState
import com.example.foodappwithcompose.viewmodel.MealDetailWithIdViewModel

@Composable
fun DetailWithMealId(navHostController: NavHostController){
    val detailViewModel : MealDetailWithIdViewModel = viewModel()
    val state by detailViewModel.state
    val context= LocalContext.current
    when(state){
        is DetailState.Loading-> LoadingComponent()
        is DetailState.Error->{
            val message = (state as DetailState.Error).error
            ErrorComponent(message,onRefreshClicked ={

            })
        }
        is DetailState.Success->{
            val data= (state as DetailState.Success).mealDetail
            data.meals.map {
                MealDetailRow(
                    appBarText = it.strMeal.toString(),
                    mealImage = it.strMealThumb.toString(),
                    mealName = it.strMeal.toString(),
                    mealCountry = it.strArea.toString(),
                    mealCategory = it.strCategory.toString(),
                    mealDescription = it.strInstructions.toString(),
                    mealYtUrl = it.strYoutube,
                    context =context
                )
            }
        }
    }
}
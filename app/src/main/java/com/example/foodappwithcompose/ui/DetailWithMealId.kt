package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.ErrorComponent
import com.example.foodappwithcompose.component.LoadingComponent
import com.example.foodappwithcompose.customlayouts.MealDetailLayout
import com.example.foodappwithcompose.state.DetailState
import com.example.foodappwithcompose.viewmodel.MealDetailWithIdViewModel

@Composable
fun DetailWithMealId(backClick: ((Unit) -> Unit)? = null) {
    val detailViewModel: MealDetailWithIdViewModel = viewModel()
    val state by detailViewModel.state
    val context = LocalContext.current
    when (state) {
        is DetailState.Loading -> LoadingComponent()
        is DetailState.Error -> {
            val message = (state as DetailState.Error).error
            ErrorComponent(message, onRefreshClicked = {
                detailViewModel.tryToGetMealDetail()
            })
        }

        is DetailState.Success -> {
            val data = (state as DetailState.Success).mealDetail
            data.meals.map {
                MealDetailLayout(
                    appBarText = it.strMeal.toString(),
                    mealImage = it.strMealThumb.toString(),
                    mealName = it.strMeal.toString(),
                    mealCountry = it.strArea.toString(),
                    mealCategory = it.strCategory.toString(),
                    mealDescription = it.strInstructions.toString(),
                    mealYtUrl = it.strYoutube,
                    context = context,
                    backClick = {
                        backClick?.invoke(Unit)
                    }
                )
            }
        }
    }
}
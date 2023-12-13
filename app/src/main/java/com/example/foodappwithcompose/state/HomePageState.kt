package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealDetailResponse

sealed class HomePageState {
    object Loading : HomePageState()
    data class Success(
        val randomMeal: MealDetailResponse,
        val searchMeal: MealDetailResponse,
        val category: CategoryResponse,
        val firstTimeRun : Boolean
    ) : HomePageState()

    data class Error(val error: String) : HomePageState()
}

package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealNameResponse

sealed class HomeMealState {
    object Loading : HomeMealState()
    data class Success(
        val randomMeal: MealNameResponse,
        val searchMeal: MealNameResponse,
        val category: CategoryResponse
    ) : HomeMealState()

    data class Error(val error: String) : HomeMealState()
}

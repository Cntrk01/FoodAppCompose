package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealDetailResponse

sealed class HomeMealState {
    object Loading : HomeMealState()
    data class Success(
        val randomMeal: MealDetailResponse,
        val searchMeal: MealDetailResponse,
        val category: CategoryResponse
    ) : HomeMealState()

    data class Error(val error: String) : HomeMealState()
}

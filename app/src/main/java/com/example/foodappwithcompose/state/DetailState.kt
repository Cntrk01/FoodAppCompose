package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.MealDetailResponse

sealed class DetailState{
    object Loading : DetailState()
    data class Success(val mealDetail : MealDetailResponse) : DetailState()
    data class Error(val error : String) : DetailState()
}

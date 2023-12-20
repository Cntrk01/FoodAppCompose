package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.MealDetailResponse

sealed class SearchState{
    object Loading : SearchState()
    data class Success(val data : MealDetailResponse) : SearchState()
    data class Error(val error : String) : SearchState()
}

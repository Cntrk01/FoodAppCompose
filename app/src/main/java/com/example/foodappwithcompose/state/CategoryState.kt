package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealsResponse

sealed class CategoryState{
    object Loading : CategoryState()
    data class Success(val categoryList : CategoryResponse) : CategoryState()
    data class Error(val error: String) : CategoryState()
}

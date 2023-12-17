package com.example.foodappwithcompose.state

import com.example.foodappwithcompose.model.MealsResponse


sealed class CategoryMealItemState{
    object Loading : CategoryMealItemState()
    data class Success(val mealDetailResponse: MealsResponse,val categoryName:String) : CategoryMealItemState()
    data class Error(val error: String) : CategoryMealItemState()
}

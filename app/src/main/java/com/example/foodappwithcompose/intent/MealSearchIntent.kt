package com.example.foodappwithcompose.intent

sealed class MealSearchIntent{
    data class SearchMeal(val query:String) : MealSearchIntent()
}

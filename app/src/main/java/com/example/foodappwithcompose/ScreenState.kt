package com.example.foodappwithcompose

sealed class ScreenState(val route:String){
    object Home : ScreenState("home")
    object Detail : ScreenState("detail")
    object Category : ScreenState("category")
    object CategoryItemDetail : ScreenState("mealId")
}

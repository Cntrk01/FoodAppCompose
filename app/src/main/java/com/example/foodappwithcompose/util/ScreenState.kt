package com.example.foodappwithcompose.util

sealed class ScreenState(val route:String){
    object Detail : ScreenState("detail")
    object Category : ScreenState("category")
    object CategoryItemDetail : ScreenState("mealId")
    object SearchBottomItem : ScreenState("search")
    object CategoryBottomItem : ScreenState("categoryList")
    object HomeBottomItem : ScreenState("home")
}

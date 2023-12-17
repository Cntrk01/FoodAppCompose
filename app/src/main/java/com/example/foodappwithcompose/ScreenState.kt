package com.example.foodappwithcompose

sealed class ScreenState(val route:String){
    object HomeBottomItem : ScreenState("home")
    object Detail : ScreenState("detail")
    object Category : ScreenState("category")
    object CategoryItemDetail : ScreenState("mealId")
    object CategorySearchItem : ScreenState("search")
    object CategoryBottomItem : ScreenState("categoryList")
}

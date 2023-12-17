package com.example.foodappwithcompose

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:Int
){
    object Home : BottomBarScreen(
        route = ScreenState.HomeBottomItem.route,
        title = "Home",
        icon = R.drawable.baseline_home_24
    )
    object Category : BottomBarScreen(
        route = ScreenState.CategoryBottomItem.route,
        title = "Category",
        icon = R.drawable.baseline_category_24
    )
    object Search : BottomBarScreen(
        route = ScreenState.CategorySearchItem.route,
        title = "Search",
        icon = R.drawable.baseline_search_24
    )
}

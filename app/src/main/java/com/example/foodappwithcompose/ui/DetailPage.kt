package com.example.foodappwithcompose.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.customlayouts.MealDetailLayout
import com.example.foodappwithcompose.model.MealDetail

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun DetailPage(navController: NavHostController) {
    val mealDetail = navController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
    val context = LocalContext.current
    mealDetail?.let {
        val trimmedText = if (it.strMeal.toString().length > 10) {
            it.strMeal.toString().substring(0, 9) + "..."
        } else {
            it.strMeal.toString()
        }
        MealDetailLayout(
            appBarText = trimmedText,
            mealImage = it.strMealThumb.toString(),
            mealName = it.strMeal.toString(),
            mealCountry = it.strArea.toString(),
            mealCategory = it.strCategory.toString(),
            mealDescription = it.strInstructions.toString(),
            mealYtUrl = it.strYoutube,
            context = context,
            navHostController = navController
        )
    }
}
package com.example.foodappwithcompose.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.foodappwithcompose.customlayouts.MealDetailRow
import com.example.foodappwithcompose.model.MealDetail

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun DetailPage(navController: NavController) {
    val mealDetail = navController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
    val context = LocalContext.current
    mealDetail?.let {
        val trimmedText = if (it.strMeal.toString().length > 10) {
            it.strMeal.toString().substring(0, 9) + "..."
        } else {
            it.strMeal.toString()
        }
        MealDetailRow(
            appBarText = trimmedText,
            mealImage = it.strMealThumb.toString(),
            mealName = it.strMeal.toString(),
            mealCountry = it.strArea.toString(),
            mealCategory = it.strCategory.toString(),
            mealDescription = it.strInstructions.toString(),
            mealYtUrl = it.strYoutube,
            context = context
        )
    }
}
package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.ScreenState
import com.example.foodappwithcompose.model.MealDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageMealItemRow(
    meals: List<MealDetail>,
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            onClick = {
                meals.map {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set("mealDetailData", it)
                }
                navHostController.navigate(ScreenState.Detail.route)
            }
        ) {
            if (meals.isNotEmpty()) {
                meals.map {
                    MealItemRow(it.strMealThumb.toString(), it.strMeal.toString())
                }
            }
        }
    }
}
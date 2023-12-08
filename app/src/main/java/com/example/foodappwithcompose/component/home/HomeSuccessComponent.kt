package com.example.foodappwithcompose.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealNameResponse

@Composable
fun HomeSuccessComponent(randomMeal:MealNameResponse,searchMeal:MealNameResponse,category:CategoryResponse){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            randomMeal.meals.let {
                it.map {randomMeal->
                    if (randomMeal.strMealThumb?.isNotEmpty() == true) {
                        AsyncImage(
                            model = randomMeal.strMealThumb,
                            contentDescription = "thumbnail",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                        )
                    }
                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {

        }

        // Üçüncü tasarım
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            // Üçüncü tasarımın içeriği
        }
    }
}
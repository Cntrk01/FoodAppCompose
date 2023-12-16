package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

//Kategoriye tıklanınca ikili gösterdiğim için GridMeal dedim
@Composable
fun MealLayout(mealImage:String, mealName:String){
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = mealImage,
            contentDescription = "meal",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 400f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = mealName,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                maxLines = 1
            )
        }
    }
}
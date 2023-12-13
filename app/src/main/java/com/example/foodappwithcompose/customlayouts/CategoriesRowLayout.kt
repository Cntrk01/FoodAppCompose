package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesRowLayout(categoriesItem: Category, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(200.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            navHostController.navigate(route = "category/"+categoriesItem.strCategory)
        })
    {
        ItemRow(mealImage = categoriesItem.strCategoryThumb, mealName = categoriesItem.strCategory)
    }
}
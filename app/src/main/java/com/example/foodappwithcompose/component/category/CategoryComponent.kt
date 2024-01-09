package com.example.foodappwithcompose.component.category

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
import com.example.foodappwithcompose.util.ScreenState
import com.example.foodappwithcompose.customlayouts.MealLayout
import com.example.foodappwithcompose.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryComponent(
    categoriesItem: Category,
    onClickItemString : ((String,String)->Unit)?=null
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(200.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            onClickItemString?.invoke(ScreenState.Category.route,categoriesItem.strCategory)
        })
    {
        MealLayout(
            mealImage = categoriesItem.strCategoryThumb,
            mealName = categoriesItem.strCategory
        )
    }
}
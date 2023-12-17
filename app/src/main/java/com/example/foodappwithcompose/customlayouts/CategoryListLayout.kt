package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.category.CategorySuccessComponent
import com.example.foodappwithcompose.model.CategoryResponse

@Composable
fun CategoryListLayout(listAlignment:String,categoryResponse: CategoryResponse,navHostController:NavHostController){
    if (listAlignment == "Horizontal"){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categoryResponse.categories) {
                CategorySuccessComponent(categoriesItem = it, navHostController = navHostController)
            }
        }
    }else if (listAlignment=="Vertical"){
        Column {
            AppBar(isVisible = false, text = "Category", color = Color.Black)
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                items(categoryResponse.categories) {
                    CategorySuccessComponent(categoriesItem = it, navHostController = navHostController)
                }
            }
        }
    }
}
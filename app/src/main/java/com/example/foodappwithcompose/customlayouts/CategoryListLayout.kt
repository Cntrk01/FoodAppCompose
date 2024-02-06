package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodappwithcompose.component.category.CategoryComponent
import com.example.foodappwithcompose.model.CategoryResponse

//Home.kt içerisinde yatay olarak listeleniyor.Bottomnavdan gidince dikey olarak kategoriler listeleniyor
@Composable
fun CategoryListLayout(
    listAlignment: String,
    categoryResponse: CategoryResponse,
    onItemClickNavigate: ((String, String) -> Unit)? = null
) {
    if (listAlignment == "Horizontal") {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categoryResponse.categories) {
                CategoryComponent(
                    categoriesItem = it,
                    onItemClickNavigate = { route, arguments ->
                        onItemClickNavigate?.invoke(route, arguments)
                    })
            }
        }
    } else if (listAlignment == "Vertical") {
        Column {
            AppBar(isVisible = false, text = "Category", color = Color.Black)
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count=categoryResponse.categories.size,
                    key={
                        categoryResponse.categories[it].idCategory
                    },
                    itemContent={
                        CategoryComponent(
                            categoriesItem = categoryResponse.categories[it],
                            onItemClickNavigate = { route, arguments ->
                                onItemClickNavigate?.invoke(route, arguments)
                            })
                    })
                }
            }
        }
}
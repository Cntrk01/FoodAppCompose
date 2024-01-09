package com.example.foodappwithcompose.bottomnavbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.ErrorComponent
import com.example.foodappwithcompose.component.LoadingComponent
import com.example.foodappwithcompose.customlayouts.CategoryListLayout
import com.example.foodappwithcompose.state.CategoryState
import com.example.foodappwithcompose.viewmodel.CategoryListViewModel

@Composable
fun Category(onItemClickCategory : ((String,String)->Unit )?=null) {
    val categoryListViewModel: CategoryListViewModel = viewModel()
    val state by categoryListViewModel.state

    when (state) {
        is CategoryState.Loading -> LoadingComponent()
        is CategoryState.Error -> {
            val error = (state as CategoryState.Error).error
            ErrorComponent(errorMessage = error) {
                categoryListViewModel.tryToGetCategory()
            }
        }
        else -> {
            val data = (state as CategoryState.Success).categoryList
            CategoryListLayout(
                listAlignment = "Vertical",
                categoryResponse = data,
                onClickItemString = { route,arguments->
                    onItemClickCategory?.invoke(route,arguments)
                }
            )
        }
    }
}
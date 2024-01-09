package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.ErrorComponent
import com.example.foodappwithcompose.component.LoadingComponent
import com.example.foodappwithcompose.customlayouts.MealsWithInCategoryLayout
import com.example.foodappwithcompose.state.CategoryMealItemState
import com.example.foodappwithcompose.viewmodel.CategoryPageViewModel

//Kategoriye tıklandığı zaman bu sayfa çalışarak kategori isimine göre altındaki yemekleri listeliyor.
@Composable
fun MealsWithInCategory(
    backClick: ((Unit) -> Unit)? = null,
    itemDetailClick: ((String, String) -> Unit)? = null
) {
    val viewModel: CategoryPageViewModel = viewModel()

    val state by viewModel.state
    when (state) {
        is CategoryMealItemState.Loading -> LoadingComponent()
        is CategoryMealItemState.Error -> {
            val message = (state as CategoryMealItemState.Error).error
            ErrorComponent(message, onRefreshClicked = {
                viewModel.tryToGetMeal()
            })
        }

        is CategoryMealItemState.Success -> {
            val data = (state as CategoryMealItemState.Success).mealDetailResponse
            val categoryName = (state as CategoryMealItemState.Success).categoryName
            MealsWithInCategoryLayout(
                mealsResponse = data,
                categoryName = categoryName,
                backClick = {
                    backClick?.invoke(Unit)
                },
                itemDetailClick = { route, arguments ->
                    itemDetailClick?.invoke(route, arguments)
                }
            )
        }
    }
}
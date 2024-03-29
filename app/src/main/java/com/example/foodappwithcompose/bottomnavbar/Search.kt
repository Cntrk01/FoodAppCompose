package com.example.foodappwithcompose.bottomnavbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.LoadingComponent
import com.example.foodappwithcompose.component.search.SearchComponent
import com.example.foodappwithcompose.intent.SearchIntent
import com.example.foodappwithcompose.state.SearchState
import com.example.foodappwithcompose.viewmodel.SearchViewModel

@Composable
fun Search(
    navHostController: NavHostController,
    onItemClickNavigate : ((String)->Unit)?=null) {
    val searchViewModel: SearchViewModel = viewModel()
    val state by searchViewModel.state
    val checkBoolean = rememberSaveable { (mutableStateOf(true)) }

    when (state) {
        is SearchState.Loading -> LoadingComponent()
        is SearchState.Error -> {
            val errorMessage = (state as SearchState.Error).error
            SearchComponent(
                navHostController = navHostController,
                queryErrorMsg = errorMessage,
                searchMeal = {
                    if (it.isNotBlank()){
                        searchViewModel.processIntent(SearchIntent.Search(it))
                    }
                })
        }

        is SearchState.Success -> {
            val data = (state as SearchState.Success).data
            checkBoolean.value = false
            SearchComponent(
                mealDetailResponse = data,
                navHostController = navHostController,
                searchMeal = {
                    searchViewModel.processIntent(SearchIntent.Search(it))
                },
                onItemClickNavigate={
                    onItemClickNavigate?.invoke(it)
                })
        }
    }

    if (checkBoolean.value) {
        LaunchedEffect(Unit) {
            searchViewModel.processIntent(SearchIntent.Loading)
        }
    }
}
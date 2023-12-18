package com.example.foodappwithcompose.bottomnavbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.ErrorComponent
import com.example.foodappwithcompose.component.LoadingComponent
import com.example.foodappwithcompose.component.search.SearchComponent
import com.example.foodappwithcompose.component.search.SearchErrorComponent
import com.example.foodappwithcompose.intent.SearchIntent
import com.example.foodappwithcompose.state.SearchState
import com.example.foodappwithcompose.viewmodel.SearchViewModel

@Composable
fun Search(navHostController: NavHostController){
    val searchViewModel : SearchViewModel = viewModel()
    val state by searchViewModel.state
    val checkBoolean = rememberSaveable { (mutableStateOf(true)) }

    when(state){
        is SearchState.Loading-> LoadingComponent()
        is SearchState.Error ->{
            val errorMessage=(state as SearchState.Error).error
            println(errorMessage)
            ErrorComponent(errorMessage = errorMessage) {
                searchViewModel.processIntent(SearchIntent.Loading)
            }
        }
        is SearchState.Success->{
            val data= (state as SearchState.Success).data
            checkBoolean.value=false
            SearchComponent(mealDetailResponse = data, navHostController = navHostController){
                searchViewModel.processIntent(SearchIntent.Search(it))
            }
        }
    }
    if (checkBoolean.value){
        LaunchedEffect(Unit){
            searchViewModel.processIntent(SearchIntent.Loading)
        }
    }
}
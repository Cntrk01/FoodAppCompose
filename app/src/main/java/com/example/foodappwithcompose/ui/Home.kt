package com.example.foodappwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.foodappwithcompose.intent.HomeIntent
import com.example.foodappwithcompose.viewmodel.HomeViewModel

@Composable
fun Home(homeViewModel: HomeViewModel){


    LaunchedEffect(Unit){
        homeViewModel.processIntent(HomeIntent.Loading)
    }
}
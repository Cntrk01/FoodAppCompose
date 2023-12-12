package com.example.foodappwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodappwithcompose.model.MealDetail
import com.example.foodappwithcompose.ui.Home
import com.example.foodappwithcompose.ui.MealDetail
import com.example.foodappwithcompose.ui.theme.FoodAppWithComposeTheme
import com.example.foodappwithcompose.viewmodel.HomeViewModel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppWithComposeTheme {
                val navController= rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
//    fun <A> String.fromJson(type: Class<A>): A {
//        return Gson().fromJson(this, type)
//    }
//    fun <A> A.toJson(): String? {
//        return Gson().toJson(this)
//    }
}

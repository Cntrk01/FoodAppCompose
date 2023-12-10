package com.example.foodappwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodappwithcompose.ui.Home
import com.example.foodappwithcompose.ui.MealDetail
import com.example.foodappwithcompose.ui.theme.FoodAppWithComposeTheme
import com.example.foodappwithcompose.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppWithComposeTheme {
                val navController= rememberNavController()
                NavHost(navController = navController, startDestination = "home_page_screen"){
                    composable("home_page_screen"){
                        Home(homeViewModel = viewModel, navController = navController)
                    }
                    composable("detail_page/{mealId}/{mealName}/{mealCategory}/{mealCountry}/{mealDescription}/{mealYoutubeUrl}",
                        arguments = listOf(
                        navArgument("mealId"){
                          type=NavType.StringType
                        },
                        navArgument("mealName"){
                           type=NavType.StringType
                        },
                        navArgument("mealCategory"){
                            type= NavType.StringType
                        },
                        navArgument("mealCountry"){
                            type= NavType.StringType
                        },
                        navArgument("mealDescription"){
                            type= NavType.StringType
                        },
                        navArgument("mealYoutubeUrl"){
                            type= NavType.StringType
                        },
                        )){
                        val mealId= remember {
                            it.arguments?.getString("mealId")
                        }
                        val mealName= remember {
                            it.arguments?.getString("mealName")
                        }
                        val mealCategory= remember {
                            it.arguments?.getString("mealCategory")
                        }
                        val mealCountry= remember {
                            it.arguments?.getString("mealCountry")
                        }
                        val mealDescription= remember {
                            it.arguments?.getString("mealDescription")
                        }
                        val mealYoutubeUrl= remember {
                            it.arguments?.getString("mealYoutubeUrl")
                        }
                        MealDetail(mealId = mealId ?: "", mealName = mealName ?: "", mealCategory = mealCategory ?: "",
                            mealCountry = mealCountry ?: "", mealDescription = mealDescription ?: "",
                            mealYoutubeUrl = mealYoutubeUrl ?: "")
                    }
                }
            }
        }
    }
}

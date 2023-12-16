package com.example.foodappwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.foodappwithcompose.ui.theme.FoodAppWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppWithComposeTheme {
                val navController= rememberNavController()
                SetupNavGraph(navHostController = navController)
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

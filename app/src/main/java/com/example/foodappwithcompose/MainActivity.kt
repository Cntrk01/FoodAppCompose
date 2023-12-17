package com.example.foodappwithcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodappwithcompose.ui.theme.FoodAppWithComposeTheme
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppWithComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(navHostController = navController)
                    }
                ) {
                    SetupNavGraph(navHostController = navController)
                }
            }
        }
    }

    @Composable
    fun BottomBar(navHostController: NavHostController) {
        val screen = listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Category,
            BottomBarScreen.Search
        )
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomAppBar {
            screen.forEach { screen ->
                AddItem(screen = screen, currentDestination = currentDestination, navHostController = navHostController)
            }
        }
    }

    @Composable
    fun RowScope.AddItem(
        screen: BottomBarScreen,
        currentDestination: NavDestination?,
        navHostController: NavHostController
    ) {
        NavigationBarItem(selected =
        currentDestination?.hierarchy?.any {
            it.route == screen.route
        }==true,
            onClick = {
                navHostController.navigate(screen.route)
            },
            alwaysShowLabel = false,
            icon = {
                Icon(painter = painterResource(id = screen.icon), contentDescription = "Icon")
            },
            label = {
                Text(text = screen.title)
            })
    }

//    fun <A> String.fromJson(type: Class<A>): A {
//        return Gson().fromJson(this, type)
//    }
//    fun <A> A.toJson(): String? {
//        return Gson().toJson(this)
//    }
}

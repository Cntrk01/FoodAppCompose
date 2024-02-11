package com.example.foodappwithcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodappwithcompose.ui.theme.FoodAppWithComposeTheme
import com.example.foodappwithcompose.util.BottomBarScreen
import com.example.foodappwithcompose.util.ScreenState
import com.example.foodappwithcompose.util.SetupNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppWithComposeTheme {
                BottomBarAnimationApp()
            }
        }
    }

    @Composable
    fun BottomBarAnimationApp() {
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            ScreenState.CategoryBottomItem.route -> {
                // Show BottomBar
                bottomBarState.value = true
            }

            ScreenState.HomeBottomItem.route -> {
                bottomBarState.value = true
            }

            ScreenState.SearchBottomItem.route -> {
                bottomBarState.value = true
            }

            else -> {
                bottomBarState.value = false
                //bu else dışındaydı eğer burası çalışmaz ise tek tek diğer state durumlarını da eklicem
                //"car_details" -> {
                // Hide BottomBar
                // bottomBarState.value = false
                // }
            }
        }
        Scaffold(
            bottomBar = {
                BottomBar(navController = navController, bottomBarState = bottomBarState)
            },
            content = { paddingValues ->
                Box{
                    SetupNavGraph(navHostController = navController)
                }
            })
    }

    @Composable
    fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
        val items = listOf(BottomBarScreen.Home, BottomBarScreen.Category, BottomBarScreen.Search)
        AnimatedVisibility(visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {
                //Bottom bar default mor olan ile geliyor zaten kütüphanesini de ekledim var gradleda
                BottomAppBar (
                    //Bottombara sağ ve sol üst radius verdik
                    //Navigation barın colorunu ayarlıyor
                    //containerColor = MaterialTheme.colorScheme.primary,
                    //Icon ve Yazı arasında boşluk veriyor
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                ){
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    items.forEach {
                        NavigationBarItem(
                            selected = currentRoute == it.route,
                            onClick = { navController.navigate(it.route) },
                            icon = {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = it.title
                                )
                            },
                            label = { Text(text = it.title) },
                        )
                    }
                }
            })//iki saattir burada {} açık oldugu için hata arıyorum :D
    }
}

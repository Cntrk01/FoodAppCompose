package com.example.foodappwithcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.intent.HomeIntent
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.HomePageState
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {
//    private val  _state = MutableStateFlow(HomeRandomVSearchMealState.Loading)
//    val state : StateFlow<HomeRandomVSearchMealState> =_state  bunda loading verdiğinde asagıda tekrar succes veremiyorum.fakat mutable da verebiliyorum

    private val _state = mutableStateOf<HomePageState>(HomePageState.Loading)
    val state: State<HomePageState> = _state

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Loading -> loadAllData()
        }
    }

    private fun loadAllData() = viewModelScope.launch {
        _state.value = HomePageState.Loading
        try {
            _state.value = HomePageState.Success(
                MealApiClient.getRandomMeal(),
                MealApiClient.searchMeal(query = "beef"),
                MealApiClient.getMealCategory(),
                false
            )
        } catch (e: Exception) {
            _state.value = HomePageState.Error(error = "Error")
        } catch (e:InternalError){
            _state.value= HomePageState.Error(error = "Internet Connection Error")
        } catch (e:ConnectTimeoutException){
            _state.value= HomePageState.Error(error = "Connect Timeout Error")
        }
    }
}
package com.example.foodappwithcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.intent.HomeIntent
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.HomeMealState
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
//    private val  _state = MutableStateFlow(HomeRandomVSearchMealState.Loading)
//    val state : StateFlow<HomeRandomVSearchMealState> =_state  bunda loading verdiğinde asagıda tekrar succes veremiyorum.fakat mutable da verebiliyorum

    private val _state =
        mutableStateOf<HomeMealState>(HomeMealState.Loading)
    val state: State<HomeMealState> = _state

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Loading -> loadAllData()
        }
    }

    private fun loadAllData() = viewModelScope.launch {
        _state.value = HomeMealState.Loading
        try {
            _state.value = HomeMealState.Success(
                MealApiClient.getRandomMeal(),
                MealApiClient.searchMeal("beef"),
                MealApiClient.getMealCategory()
            )
        } catch (e: Exception) {
            _state.value = HomeMealState.Error("Error")
        } catch (e:InternalError){
            _state.value= HomeMealState.Error("Internet Connection Error")
        } catch (e:ConnectTimeoutException){
            _state.value= HomeMealState.Error("Connect Timeout Error")
        }
    }
}
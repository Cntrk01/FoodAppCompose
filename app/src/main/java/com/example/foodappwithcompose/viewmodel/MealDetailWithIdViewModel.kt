package com.example.foodappwithcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.state.DetailState
import androidx.compose.runtime.State
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.HomePageState
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.launch

class MealDetailWithIdViewModel(private val savedStateHandle : SavedStateHandle) : ViewModel() {
    private val _state = mutableStateOf<DetailState>(DetailState.Loading)
    val state : State<DetailState> = _state

    private var mealId=""
    init {
        savedStateHandle.get<String>("mealId")?.let {
            mealId=it
        }
        getMealDetail(fetchData=mealId)
    }

    private fun getMealDetail(fetchData:String)=viewModelScope.launch {
        _state.value=DetailState.Loading
        try {
            _state.value=DetailState.Success(
                MealApiClient.getMealDetailWithId(mealId = fetchData)
            )
        }catch (e: Exception) {
            _state.value = DetailState.Error(error = "Error")
        } catch (e:InternalError){
            _state.value= DetailState.Error(error ="Internet Connection Error")
        } catch (e: ConnectTimeoutException){
            _state.value= DetailState.Error(error ="Connect Timeout Error")
        }
    }
    fun tryToGetMealDetail(){
        getMealDetail(fetchData=mealId)
    }
}
package com.example.foodappwithcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.CategoryPageState
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.launch
class CategoryPageViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _state = mutableStateOf<CategoryPageState>(CategoryPageState.Loading)
    val state: State<CategoryPageState> = _state
    private var categoryId=""
    init {
        savedStateHandle.get<String>("categoryId")?.let {
            getMealByCategory(it)
            categoryId=it
            println(
                it
            )
        }
    }

    private fun getMealByCategory(categoryId:String)=viewModelScope.launch {
        _state.value = CategoryPageState.Loading
        try {
            val fetchData=MealApiClient.getMealInCategory(categoryId)
            if (fetchData.meals.isNotEmpty()){
                _state.value=CategoryPageState.Success(fetchData)
            }else{
                _state.value = CategoryPageState.Error("No food found in this category")
            }
        }catch (e: Exception) {
            _state.value = CategoryPageState.Error("Error")
        } catch (e:InternalError){
            _state.value= CategoryPageState.Error("Internet Connection Error")
        } catch (e: ConnectTimeoutException){
            _state.value= CategoryPageState.Error("Connect Timeout Error")
        }
    }

    fun tryToGetMeal(){
        getMealByCategory(categoryId = categoryId)
    }

}
package com.example.foodappwithcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.CategoryMealItemState
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.launch
class CategoryPageViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _state = mutableStateOf<CategoryMealItemState>(CategoryMealItemState.Loading)
    val state: State<CategoryMealItemState> = _state
    private var categoryName=""
    init {
        savedStateHandle.get<String>("categoryName")?.let {
            getMealByCategory(categoryId=it)
            categoryName=it
        }
    }

    private fun getMealByCategory(categoryId:String)=viewModelScope.launch {
        _state.value = CategoryMealItemState.Loading
        try {
            val fetchData=MealApiClient.getMealInCategory(categoryId)
            if (fetchData.meals.isNotEmpty()){
                _state.value=CategoryMealItemState.Success(mealDetailResponse = fetchData, categoryName = categoryName)
            }else{
                _state.value = CategoryMealItemState.Error(error ="No food found in this category")
            }
        }catch (e: Exception) {
            _state.value = CategoryMealItemState.Error(error = "Error")
        } catch (e:InternalError){
            _state.value= CategoryMealItemState.Error(error ="Internet Connection Error")
        } catch (e: ConnectTimeoutException){
            _state.value= CategoryMealItemState.Error(error ="Connect Timeout Error")
        }
    }
    fun tryToGetMeal(){
        getMealByCategory(categoryId = categoryName)
    }

}
package com.example.foodappwithcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.CategoryState
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {
    private var isRequestMade = true
    private val _state = mutableStateOf<CategoryState>(CategoryState.Loading)
    val state : State<CategoryState> = _state

    init {
        //Her seferinde istek atıp apiden alıyordu şuan almıyor.Sadece bottomnavda farklı yere gidip gelince istek atıyor.
        //Her seferinde istek atma sebebi de init methodunun çalışmasından dolayı.Home pagedede buna benzer bir mantık yaptım
        if (isRequestMade){
            getCategories()
        }
    }
    private fun getCategories() = viewModelScope.launch {
        _state.value=CategoryState.Loading
        try {
            _state.value=CategoryState.Success(MealApiClient.getMealCategory())
            isRequestMade=false
        }catch (e: Exception) {
            _state.value = CategoryState.Error(error = "Error")
        } catch (e:InternalError){
            _state.value= CategoryState.Error(error ="Internet Connection Error")
        } catch (e: ConnectTimeoutException){
            _state.value= CategoryState.Error(error ="Connect Timeout Error")
        }
    }
}
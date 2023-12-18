package com.example.foodappwithcompose.viewmodel

import android.content.res.Resources.NotFoundException
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappwithcompose.intent.SearchIntent
import com.example.foodappwithcompose.network.MealApiClient
import com.example.foodappwithcompose.state.SearchState
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.launch
import java.net.ConnectException

class SearchViewModel : ViewModel() {
    private val _state = mutableStateOf<SearchState>(SearchState.Loading)
    val state: State<SearchState> = _state

    fun processIntent(searchIntent: SearchIntent) {
        when (searchIntent) {
            is SearchIntent.Loading -> loadRandomMeal()
            is SearchIntent.Search -> searchRecipe(searchIntent.query)
        }
    }

    private fun searchRecipe(query: String)=viewModelScope.launch {
        _state.value = SearchState.Loading
        try {
            _state.value=SearchState.Success(data = MealApiClient.searchMeal(query = query))
        }
        catch (e:NotFoundException){
            _state.value = SearchState.Error(error = "Meal Not Found...")
        }
        catch (e: ConnectException) {
            _state.value = SearchState.Error(error = "Internet Connection Error: ${e.message}")
        } catch (e: SocketTimeoutException) {
            _state.value = SearchState.Error(error = "Connect Timeout Error: ${e.message}")
        } catch (e: ConnectTimeoutException) {
            _state.value = SearchState.Error(error = "Connect Timeout Error")
        } catch (e: Exception) {
            _state.value = SearchState.Error(error = "Error")
        }
    }

    private fun loadRandomMeal() = viewModelScope.launch {
        _state.value = SearchState.Loading
        try {
            _state.value = SearchState.Success(data = MealApiClient.getRandomMeal())
        } catch (e: Exception) {
            _state.value = SearchState.Error(error = "Error")
        }catch (e: ConnectException) {
            _state.value = SearchState.Error(error = "Internet Connection Error: ${e.message}")
        } catch (e: SocketTimeoutException) {
            _state.value = SearchState.Error(error = "Connect Timeout Error: ${e.message}")
        } catch (e: ConnectTimeoutException) {
            _state.value = SearchState.Error(error = "Connect Timeout Error")
        }
    }
}
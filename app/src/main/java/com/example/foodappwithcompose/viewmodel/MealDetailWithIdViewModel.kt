package com.example.foodappwithcompose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MealDetailWithIdViewModel(private val savedStateHandle : SavedStateHandle) : ViewModel() {

    private var mealId=""
    init {
        savedStateHandle.get<String>("mealId")?.let {
            mealId=it
            println(it)
        }
    }
}
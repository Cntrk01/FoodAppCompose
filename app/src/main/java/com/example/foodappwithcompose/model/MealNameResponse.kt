package com.example.foodappwithcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class MealNameResponse(
    val meals: List<MealName>
)
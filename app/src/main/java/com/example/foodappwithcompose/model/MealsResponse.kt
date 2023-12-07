package com.example.foodappwithcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class MealsResponse(
    val meals: List<Meals>
)
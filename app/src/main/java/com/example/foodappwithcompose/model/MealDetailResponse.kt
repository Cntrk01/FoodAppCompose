package com.example.foodappwithcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class MealDetailResponse(
    val meals: List<MealDetail>
)
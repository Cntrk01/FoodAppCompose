package com.example.foodappwithcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val categories: List<Category>
)
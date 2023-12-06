package com.example.foodappwithcompose.network

import com.example.foodappwithcompose.model.MealName
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object MealApiClient {
    private val apiClient = HttpClient(CIO){
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getMealName(query: String): MealName {
        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s=$query"
        return apiClient.get(url).body() as MealName
    }


}
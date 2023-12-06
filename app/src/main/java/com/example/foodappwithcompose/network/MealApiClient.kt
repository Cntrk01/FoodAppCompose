package com.example.foodappwithcompose.network

import com.example.foodappwithcompose.model.CategoryResponse
import com.example.foodappwithcompose.model.MealsResponse
import com.example.foodappwithcompose.model.MealNameResponse
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
    //Yemek arayabiliyoruz
    suspend fun searchMeal(query: String): MealNameResponse {
        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s=$query"
        return apiClient.get(url).body() as MealNameResponse
    }
    suspend fun getRandomMeal() : MealNameResponse {
        val url = "https://www.themealdb.com/api/json/v1/1/random.php"
        return apiClient.get(url).body() as MealNameResponse
    }
    suspend fun getMealCategory() : CategoryResponse{
        val url = "https://www.themealdb.com/api/json/v1/1/categories.php"
        return apiClient.get(url).body() as CategoryResponse
    }
    //tıklanan kategorinin altındaki yemekleri getiriyor
    suspend fun getMealInCategory(query: String) : MealsResponse{
        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=$query"
        return apiClient.get(url).body() as MealsResponse
    }
    //buda tıklanan yemeğin detaylarını getiriyor
    suspend fun getMealDetailWithId(mealId: String) : MealNameResponse{
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=$mealId"
        return apiClient.get(url).body() as MealNameResponse
    }
}
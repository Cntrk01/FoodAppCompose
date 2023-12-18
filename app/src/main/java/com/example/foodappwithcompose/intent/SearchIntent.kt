package com.example.foodappwithcompose.intent

sealed class SearchIntent{
    data class Search(val query:String) : SearchIntent()
    object Loading : SearchIntent()
}

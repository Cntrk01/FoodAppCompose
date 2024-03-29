package com.example.foodappwithcompose.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodappwithcompose.component.ErrorComponent
import com.example.foodappwithcompose.customlayouts.AppBar
import com.example.foodappwithcompose.customlayouts.SingleMealLayout
import com.example.foodappwithcompose.model.MealDetailResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    mealDetailResponse: MealDetailResponse? = null,
    navHostController: NavHostController,
    queryErrorMsg: String? = null,
    searchMeal: ((String) -> Unit)? = null,
    onItemClickNavigate : ((String)->Unit)?=null
) {
    var query by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column {
        AppBar(isVisible = false, text = "Search Meal", color = Color.Black)

        Spacer(modifier = Modifier.padding(bottom = 3.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            value = query,
            onValueChange = {
                if (it.isNotBlank()) {
                    errorMessage = ""
                }
                query = it
            },
            label = { Text("Search") },
            singleLine = true,
            isError = errorMessage.isNotBlank(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (query.isNotBlank()) {
                            searchMeal?.invoke(query)
                        } else {
                            errorMessage = "Enter a query first"
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Clear",
                        tint = Color.Gray
                    )
                }
            }
        )
        if (queryErrorMsg?.isNotBlank() == true) {
            if (queryErrorMsg == "Meal Not Found...") {
                Text(
                    text = queryErrorMsg,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            } else {
                ErrorComponent(errorMessage = queryErrorMsg) {
                    searchMeal?.invoke(query)
                }
            }
        }
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        mealDetailResponse?.meals?.let {
            SingleMealLayout(
                meals = it,
                modifier = Modifier,
                navHostController = navHostController,
                onItemClickNavigate = {navigate->
                    onItemClickNavigate?.invoke(navigate)
                }
            )
        }
    }
}
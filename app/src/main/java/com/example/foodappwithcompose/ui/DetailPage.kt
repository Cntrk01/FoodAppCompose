package com.example.foodappwithcompose.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodappwithcompose.R
import com.example.foodappwithcompose.customlayouts.AppBar
import com.example.foodappwithcompose.model.MealDetail

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun DetailPage(navController: NavController) {
    val mealDetail =
        navController.previousBackStackEntry?.savedStateHandle?.get<MealDetail>("mealDetailData")
    val context = LocalContext.current
    mealDetail?.let {
        val trimmedText = if (it.strMeal.toString().length > 10) {
            it.strMeal.toString().substring(0, 9) + "..."
        } else {
            it.strMeal.toString()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AppBar(text = trimmedText, Color.Black)
            Box {
                AsyncImage(
                    model = it.strMealThumb,
                    contentDescription = "thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = it.strMeal.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    fontSize = 19.sp
                )
            }
            Row(
                modifier = Modifier.padding(top = 10.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = "location",
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = it.strArea.toString(),
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_category_24),
                    contentDescription = "category",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Text(
                    text = it.strCategory.toString(),
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Column {
                Text(
                    text = it.strInstructions.toString(),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .weight(3f)
                        .verticalScroll(rememberScrollState())
                )
                Image(
                    painter = painterResource(id = R.drawable.youtube_icon),
                    contentDescription = "Youtube Icon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .clickable {
                            if (it.strYoutube.isNotEmpty()) {
                                val builder = CustomTabsIntent.Builder()
                                val customTabsIntent = builder.build()
                                customTabsIntent.launchUrl(context, Uri.parse(it.strYoutube))
                            } else {
                                Toast
                                    .makeText(context, "Not Found Youtube Video", Toast.LENGTH_SHORT).show()
                            }
                        }
                )
            }
        }

    }
}
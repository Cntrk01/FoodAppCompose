package com.example.foodappwithcompose.customlayouts

import android.content.Context
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodappwithcompose.R

@Composable
fun MealDetailRow(
    appBarText:String,
    mealImage:String,
    mealName:String,
    mealCountry:String,
    mealCategory:String,
    mealDescription:String,
    mealYtUrl:String,
    context:Context
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AppBar(text = appBarText, Color.Black)
        Box {
            AsyncImage(
                model = mealImage,
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = mealName,
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
                text = mealCountry,
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
                text = mealCategory,
                modifier = Modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Bold
            )
        }

        Column {
            Text(
                text = mealDescription,
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
                        if (mealYtUrl.isNotEmpty()) {
                            val builder = CustomTabsIntent.Builder()
                            val customTabsIntent = builder.build()
                            customTabsIntent.launchUrl(context, Uri.parse(mealYtUrl))
                        } else {
                            Toast
                                .makeText(context, "Not Found Youtube Video", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
        }
    }
}
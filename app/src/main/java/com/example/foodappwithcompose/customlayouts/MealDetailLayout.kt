package com.example.foodappwithcompose.customlayouts

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodappwithcompose.R

//Tıklanan yemeğin detayını gösterir
@Composable
fun MealDetailLayout(
    appBarText:String,
    mealImage:String,
    mealName:String,
    mealCountry:String,
    mealCategory:String,
    mealDescription:String,
    mealYtUrl:String,
    context:Context,
    backClick: ((Unit) -> Unit)? = null
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AppBar(isVisible = true,text = appBarText, Color.Black, backClick = {
            backClick?.invoke(Unit)
        })
        Box (modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(8.dp))){
            MealLayout(mealImage = mealImage, mealName = mealName)
        }

        Row(
            modifier = Modifier.padding(10.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_location_on_24),
                contentDescription = "location",
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Location : $mealCountry",
                modifier = Modifier.padding(start = 2.dp),
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_category_24),
                contentDescription = "Back Button",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Category : $mealCategory",
                modifier = Modifier.padding(start = 2.dp),
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
                            Toast.makeText(context, "Not Found Youtube Video", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
        }
    }
}
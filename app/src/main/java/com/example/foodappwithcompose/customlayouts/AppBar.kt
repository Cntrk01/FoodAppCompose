package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodappwithcompose.R

@Composable
fun AppBar(isVisible: Boolean, text: String, color: Color, backClick: ((Unit) -> Unit)? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .height(60.dp)
            .padding(start = 5.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = null,
            modifier = Modifier.padding(start = 5.dp)
                .size(if (isVisible) 24.dp else 0.dp)
                .clickable {
                    backClick?.invoke(Unit)
                }
        )
        Text(
            text = text,
            fontSize = 24.sp,
            color = color,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp),
            maxLines = 1
        )
    }
}
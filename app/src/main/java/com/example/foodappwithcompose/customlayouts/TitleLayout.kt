package com.example.foodappwithcompose.customlayouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleLayout(title:String){
        Text(text = title, modifier = Modifier
            .padding(start = 14.dp),
            color = Color.Black,
            fontWeight= FontWeight.ExtraBold,
            fontSize = 18.sp,
            textDecoration= TextDecoration.Underline,
            fontFamily = FontFamily.Serif
        )
}
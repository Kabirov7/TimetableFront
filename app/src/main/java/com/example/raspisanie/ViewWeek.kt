package com.example.raspisanie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ViewWeek(item: Week)
{
        Row(
            modifier = Modifier
                .padding(vertical = 100.dp)

        ) {

            for (i in 0..6)
                Button(
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    shape = CircleShape,
                    onClick = {
                              BЩЧ
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                ) {
                    Text(
                        text = item.list[i].date
                    )
                }
    }


}
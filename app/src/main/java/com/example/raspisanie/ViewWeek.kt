package com.example.raspisanie

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
            .padding(vertical = 20.dp)

    ) {

        for (i in 0..6)
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                shape = CircleShape,
                onClick = {
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

    Column(
        modifier = Modifier
            .padding(vertical = 100.dp, horizontal = 10.dp)
            .verticalScroll(
                rememberScrollState()
            ),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 6.dp,
        ) {

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 6.dp
        ) {

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 6.dp,
        ) {

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 6.dp,
        ) {

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 6.dp,
        ) {

        }
    }


}
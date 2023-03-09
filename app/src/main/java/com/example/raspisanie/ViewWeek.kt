package com.example.raspisanie

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspisanie.ui.theme.Blueee
import com.example.raspisanie.ui.theme.Orrrange

@Composable
fun ViewWeek(item: Week)
{
// Фон
//    Image(
//        painter = painterResource(id = R.drawable.fon2),
//        contentDescription = null,
//        modifier = Modifier
//            .fillMaxSize(),
//        contentScale = ContentScale.FillHeight
//    )


// Линия дней
    Row(
        modifier = Modifier
            .padding(vertical = 25.dp, horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        for (i in 0..6)
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                shape = CircleShape,
                onClick = {
                },
                modifier = Modifier
                    .size(55.dp).border(width = 1.dp,Color.DarkGray, shape = CircleShape)
            ) {
                Text(
                    text = item.list[i].date
                )
            }
    }

    // Колонна карточек с парами
        Column(
            modifier = Modifier
                .padding(vertical = 100.dp, horizontal = 10.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 6.dp,
                border = BorderStroke(3.dp,Blueee)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp,Color.DarkGray)
                            .background(Blueee),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.list[0].listOfLessons[0].time,
                            fontSize = 20.sp,
                            color = Color.LightGray,
                        )
                        Text(
                            text = "14:10",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    }
                    Column() {

                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 6.dp,
                border = BorderStroke(3.dp,Orrrange)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp,Color.DarkGray)
                            .background(Orrrange),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.list[0].listOfLessons[0].time,
                            fontSize = 20.sp,
                            color = Color.LightGray,
                        )
                        Text(
                            text = "14:10",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    }
                    Column() {

                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 6.dp,
                border = BorderStroke(3.dp,Blueee)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp,Color.DarkGray)
                            .background(Blueee),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.list[0].listOfLessons[0].time,
                            fontSize = 20.sp,
                            color = Color.LightGray,
                        )
                        Text(
                            text = "14:10",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    }
                    Column() {

                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 6.dp,
                border = BorderStroke(3.dp,Orrrange)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp,Color.DarkGray)
                            .background(Orrrange),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.list[0].listOfLessons[0].time,
                            fontSize = 20.sp,
                            color = Color.LightGray,
                        )
                        Text(
                            text = "14:10",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    }
                    Column() {

                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 6.dp,
                border = BorderStroke(3.dp,Blueee)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp,Color.DarkGray)
                            .background(Blueee),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.list[0].listOfLessons[0].time,
                            fontSize = 20.sp,
                            color = Color.LightGray,
                        )
                        Text(
                            text = "14:10",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    }
                    Column() {

                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 6.dp,
                border = BorderStroke(3.dp,Orrrange)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp,Color.DarkGray)
                            .background(Orrrange),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.list[0].listOfLessons[0].time,
                            fontSize = 20.sp,
                            color = Color.LightGray,
                        )
                        Text(
                            text = "14:10",
                            fontSize = 20.sp,
                            color = Color.LightGray
                        )
                    }
                    Column() {

                    }
                }
            }
        }


}
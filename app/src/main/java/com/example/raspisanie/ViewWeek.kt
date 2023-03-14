package com.example.raspisanie

import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspisanie.ui.theme.Blueee
import com.example.raspisanie.ui.theme.Orrrange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.analytics.AnalyticsService
import com.mrerror.singleRowCalendar.SingleRowCalendar
import com.mrerror.singleRowCalendar.WeekDaysHeader
import com.mrerror.singleRowCalendar.weekFinalDays
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalSnapperApi::class, ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun ViewWeek(item: Week)
{

/*Фон
    Image(
        painter = painterResource(id = R.drawable.fon3),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )*/ // фон


Column(
modifier = Modifier.fillMaxSize()
) {

    val items = listOf(
        Day(listOf(
            Lesson("12:40", "Лолов Л.Л.", "Высшая математика","Лекция","246*"),
            Lesson("12:40", "Лолов Л.Л.", "Высшая математика","Лекция","246*"),
            Lesson("12:40", "Лолов Л.Л.", "Высшая математика","Лекция","246*"),
        ), "06", "Пн", "марта"),
        Day(listOf(Lesson("12:40", "Lolov", "Math","Lection","246*")), "07", "Вт", "марта"),
        Day(listOf(Lesson("12:40", "Lolov", "Math","Lection","246*")), "08", "Ср", "марта"),
        Day(listOf(Lesson("12:40", "Lolov", "Math","Lection","246*")), "09", "Чт", "марта"),
        Day(listOf(Lesson("12:40", "Lolov", "Math","Lection","246*")), "10", "Пт", "марта"),
        Day(listOf(), "11", "Сб", "марта"),
        Day(listOf(), "12", "Вс", "марта"),
    ) // дни недели


    val coroutineScope = rememberCoroutineScope()

    var day by remember { mutableStateOf(Date()) }

    //val page = remember { mutableStateOf(day.day - 1)}

    val statepage = rememberPagerState(day.day - 1)

    /*// старая линия дней
    Row(
        modifier = Modifier
            .padding(vertical = 25.dp, horizontal = 10.dp)
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        for (i in 0..6)
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                shape = CircleShape,
                onClick = {
                },
                modifier = Modifier
                    .size(55.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.list[i].date
                    )
                    Text(text = item.list[i].dayOfWeek)
                }

            }
    }*/

    SingleRowCalendar(onSelectedDayChange = { // верхний календарь

        day = it
        coroutineScope.launch {
            statepage.animateScrollToPage(
                page =
                if (day.day == 0) 6
            else day.day-1
            ) // анимация при нажатии на день недели календаря
        }

    },
        selectedDayBackgroundColor = Blueee
    )


    // Колонна карточек с парами

/*    Row( // старая версия столбцов пар
        modifier = Modifier
            .swipeable(
                state = rememberSwipeableState(initialValue = 0),
                anchors = mapOf(0f to 0, with(LocalDensity.current) { (400.dp).toPx() } to 1),
                thresholds = { _, _ -> FractionalThreshold(0.7f) },
                orientation = Orientation.Horizontal,
            ),

    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .verticalScroll(
                    state = rememberScrollState()
                )
                .width(400.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 20.dp,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Время пары
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp, Color.DarkGray)
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

                    // Описание пары
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = item.list[0].listOfLessons[0].typeOfLesson,
                            fontSize = 15.sp,
                            color = Color.Gray,
                            fontFamily = FontFamily.SansSerif
                        )
                        Text(
                            text = item.list[0].listOfLessons[0].discipline,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = FontFamily.SansSerif
                        )


                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.prepod),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 20.dp, height = 20.dp),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                text = item.list[0].listOfLessons[0].teacher,
                                fontSize = 15.sp,
                                color = Color.Gray,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.aud),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                            )
                            Text(
                                text = item.list[0].listOfLessons[0].auditory,
                                fontSize = 15.sp,
                                color = Color.Gray,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }



                    }

                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 20.dp,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Время пары
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .fillMaxHeight()
                            .border(width = 0.5.dp, Color.DarkGray)
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

                    // Описание пары
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = item.list[0].listOfLessons[0].typeOfLesson,
                            fontSize = 15.sp,
                            color = Color.Gray,
                            fontFamily = FontFamily.SansSerif
                        )
                        Text(
                            text = item.list[0].listOfLessons[0].discipline,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = FontFamily.SansSerif
                        )


                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.prepod),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 20.dp, height = 20.dp),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                text = item.list[0].listOfLessons[0].teacher,
                                fontSize = 15.sp,
                                color = Color.Gray,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.aud),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                            )
                            Text(
                                text = item.list[0].listOfLessons[0].auditory,
                                fontSize = 15.sp,
                                color = Color.Gray,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }



                    }

                }
            }
        }

    }*/

    HorizontalPager( // новый пейджер дней недели
        count = items.size,
        state = statepage,
        modifier = Modifier
            .weight(10f)
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.Top
    ) { currentPage ->


        Column(
            modifier = Modifier
                .verticalScroll(
                    state = rememberScrollState()
                )
                .fillMaxSize(),
        ) {
            if (items[currentPage].listOfLessons.isEmpty() && items[currentPage].dayOfWeek == "Сб")
            {
                Column(

                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 20.dp).fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chill),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(15.dp)
                            .shadow(10.dp, shape = RoundedCornerShape(70.dp))
                    )
                    Text(
                        text = "Пар нема!", fontSize = 25.sp, modifier = Modifier.padding(vertical = 20.dp)
                    )
                }

            }
            else if (items[currentPage].listOfLessons.isEmpty() && items[currentPage].dayOfWeek == "Вс")
            {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 20.dp).fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chill1),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(15.dp)
                            .shadow(10.dp, shape = RoundedCornerShape(70.dp))
                    )
                    Text(
                        text = "Пар нема!", fontSize = 25.sp, modifier = Modifier.padding(vertical = 20.dp)
                    )
                }

            }
            else
            {
                for (lesson in items[currentPage].listOfLessons)
                {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .clickable {},
                        shape = RoundedCornerShape(15.dp),
                        elevation = 15.dp,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            // Время пары
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .border(width = 0.5.dp, Color.DarkGray)
                                    .background(Blueee),
                                verticalArrangement = Arrangement.SpaceAround,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = lesson.time,
                                    fontSize = 20.sp,
                                    color = Color.LightGray,
                                )
                                Text(
                                    text = "14:10",
                                    fontSize = 20.sp,
                                    color = Color.LightGray
                                )
                            }

                            // Описание пары
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {

                                Text(
                                    text = lesson.typeOfLesson,
                                    fontSize = 15.sp,
                                    color = Color.Gray,
                                    fontFamily = FontFamily.SansSerif
                                )
                                Text(
                                    text = lesson.discipline,
                                    fontSize = 20.sp,
                                    color = Color.Black,
                                    fontFamily = FontFamily.SansSerif
                                )


                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.prepod),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(width = 20.dp, height = 20.dp),
                                        contentScale = ContentScale.Crop
                                    )

                                    Text(
                                        text = lesson.teacher,
                                        fontSize = 15.sp,
                                        color = Color.Gray,
                                        fontFamily = FontFamily.SansSerif,
                                        modifier = Modifier.padding(horizontal = 10.dp)
                                    )
                                }

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.aud),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(15.dp),
                                    )
                                    Text(
                                        text = lesson.auditory,
                                        fontSize = 15.sp,
                                        color = Color.Gray,
                                        fontFamily = FontFamily.SansSerif,
                                        modifier = Modifier.padding(horizontal = 10.dp)
                                    )
                                }



                            }

                        }
                    }
                }
            }



        }
    }

}



}
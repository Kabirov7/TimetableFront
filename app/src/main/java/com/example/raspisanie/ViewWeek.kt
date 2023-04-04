package com.example.raspisanie

import android.annotation.SuppressLint
import android.text.style.UpdateLayout
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
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
import com.google.accompanist.pager.*
import com.google.android.gms.analytics.AnalyticsService
import com.mrerror.singleRowCalendar.SingleRowCalendar
import com.mrerror.singleRowCalendar.WeekDaysHeader
import com.mrerror.singleRowCalendar.weekFinalDays
import com.mrerror.singleRowCalendar.weekWasSwiped
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.*
import kotlin.math.absoluteValue

var stateofpager: Date = Date()

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalSnapperApi::class, ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun ViewWeek()
{

Column(
modifier = Modifier.fillMaxSize()
) {

    val items = listOf(
        Day(listOf( // четная
            Lesson("14:55", "16:25","Абсалямова И.А.", "ИН.ЯЗ.","Практическое занятие","309*"),
            Lesson("16:45", "18:15","Галайдин П.А.", "ТОЭ","Практическое занятие","507*а"),
        ),  "Пн",),
        Day(listOf(
            Lesson("9:00", "10:30","Устиновский Г.С.", "СИСТЕМНОЕ ПО","Лекция","325*"),
            Lesson("10:50", "12:20","Трилис А.В.", "ФИЗ.ОСН.МИКРОЭЛ","Лекция","324*"),
            Lesson("12:40", "14:10","", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
        ),  "Вт"),
        Day(listOf(
            Lesson("10:50", "12:20","Воробьева Е.Е.", "ОСН.СИСТ.АН.","Практическое занятие","259"),
            Lesson("12:40", "14:10","Александрова Е.Б", "ВЫСШ.МАТЕМАТ.","Лекция","451"),
            Lesson("14:55", "16:25","Живулин В.А.", "ФИЗИКА","Лекция","325"),
            Lesson("16:45", "18:15","Галайдин П.А.", "ТОЭ","Лекция","429*"),
        ),  "Ср"),
        Day(listOf(
            Lesson("14:55", "16:25","", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
            Lesson("16:45", "18:15","Мажайцев Е.А.", "КОМП.ПРАКТИКУМ","Практическое занятие","219*а"),
        ),  "Чт",),
        Day(listOf(
            Lesson("10:50", "12:20","Волкова М.В.", "ФИЗ.ОСН.МИКРОЭЛ","Лабораторная работа","430"),
            Lesson("12:40", "14:10","Щеглова А.В.", "ВЫСШ.МАТЕМАТ.","Практическое занятие","420*"),
        ),  "Пт",),
        Day(listOf(),  "Сб"),
        Day(listOf(),  "Вс"),


        Day(listOf( // нечетная
            Lesson("12:40", "14:10","Устиновский Г.С.", "СИСТЕМНОЕ ПО","Практическое занятие","258*"),
            Lesson("14:55", "16:25","Абсалямова И.А.", "ИН.ЯЗ.","Практическое занятие","309*"),
            Lesson("16:45", "18:15","Галайдин П.А.", "ТОЭ","Практическое занятие","507*а"),
        ),  "Пн",),
        Day(listOf(
            Lesson("9:00", "10:30","Устиновский Г.С.", "СИСТЕМНОЕ ПО","Лекция","325*"),
            Lesson("10:50", "12:20","Трилис А.В.", "ФИЗ.ОСН.МИКРОЭЛ","Лекция","324*"),
            Lesson("12:40", "14:10","", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
        ),  "Вт"),
        Day(listOf(
            Lesson("10:50", "12:20","Дмитриева А.П.", "ПРАВОВЕДЕНИЕ","Практическое занятие","484"),
            Lesson("12:40", "14:10","Александрова Е.Б", "ВЫСШ.МАТЕМАТ.","Лекция","451"),
            Lesson("14:55", "16:25","Живулин В.А.", "ФИЗИКА","Лекция","325"),
            Lesson("16:45", "18:15","Галайдин П.А.", "ТОЭ","Лекция","429*"),
        ),  "Ср"),
        Day(listOf(
            Lesson("10:50", "12:20","Степанова О.Ю.", "ПРАВОВЕДЕНИЕ","Лекция","313"),
            Lesson("12:40", "14:10","Воробьева Е.Е.", "ОСН.СИСТ.АН.","Лекция","315"),
            Lesson("14:55", "16:25","", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
            Lesson("16:45", "18:15","Мажайцев Е.А.", "КОМП.ПРАКТИКУМ","Практическое занятие","219*а"),
        ),  "Чт",),
        Day(listOf(
            Lesson("12:40", "14:10","Щеглова А.В.", "ВЫСШ.МАТЕМАТ.","Практическое занятие","420*"),
            Lesson("14:55", "16:25","Лойко А.В.", "ТОЭ","Лабораторная работа","356*"),
            Lesson("16:45", "18:15","Мажайцев Е.А.", "КОМП.ПРАКТИКУМ","Практическое занятие","258*")
        ),  "Пт",),
        Day(listOf(), "Сб"),
        Day(listOf(),  "Вс"),
    ) // дни недели

   /* val items = listOf(
        Day(listOf( // четная
            Lesson("12:40", "14:10","Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лабораторная работа","114"),
            Lesson("14:55", "16:25","Гусев А.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
        ),  "Пн",),
        Day(listOf(
            Lesson("10:50", "12:20","Егоров В.В.", "АРТИЛЛ. ТЕХНИКА","Лабораторная работа","СК-1"),
            Lesson("12:40", "14:10","Маслов Д.В.", "АНАЛИЗ КОНСТР. АИУС","Практическое занятие","СК-14"),
            Lesson("14:55", "16:25","Митюшов А.И.", "РАДИОФИЗИКА","Практическое занятие","115"),
            Lesson("16:45", "18:15","Митюшов А.И.", "РАДИОФИЗИКА","Лекция","115"),
        ),  "Вт"),
        Day(listOf(
            Lesson("10:50", "12:20","Перов Л.Д.", "РАДИОФИЗИКА","Лабораторная работа","251"),
            Lesson("12:40", "14:10","Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
            Lesson("14:55", "16:25","Бурковецкий К.А.;Никольченко Ю.А.", " РАКЕТН. ТЕХНИКА","Лабораторная работа","СК21; СК21а"),
        ),  "Ср"),
        Day(listOf(
            Lesson("9:00", "10:30","Гусев А.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
            Lesson("10:50", "12:20","Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
            Lesson("12:40", "14:10","Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лекция","115"),
            Lesson("14:55", "16:25","Гусев А.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
        ),  "Чт",),
        Day(listOf(
            Lesson("10:50", "12:20","Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Лекция","440"),
            Lesson("12:40", "14:10","Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Практическое занятие","440"),
        ),  "Пт",),
        Day(listOf(
            Lesson("9:00", "10:30","Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Лекция","115"),
            Lesson("10:50", "12:20","Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Практическое занятие","251"),
        ),  "Сб"),
        Day(listOf(),  "Вс"),

        Day(listOf( // нечетная
            Lesson("10:50", "12:20","Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Практическое занятие","114"),
            Lesson("12:40", "14:10","Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лабораторная работа","114"),
            Lesson("14:55", "16:25","Крылов В.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
        ),  "Пн",),
        Day(listOf(
            Lesson("10:50", "12:20","Егоров В.В.", "АРТИЛЛ. ТЕХНИКА","Лабораторная работа","СК-1"),
            Lesson("12:40", "14:10","Маслов Д.В.", "АНАЛИЗ КОНСТР. АИУС","Практическое занятие","СК-14"),
            Lesson("14:55", "16:25","Митюшов А.И.", "РАДИОФИЗИКА","Практическое занятие","115"),
            Lesson("16:45", "18:15","Митюшов А.И.", "РАДИОФИЗИКА","Лекция","115"),
        ),  "Вт"),
        Day(listOf(
            Lesson("10:50", "12:20","Перов Л.Д.", "РАДИОФИЗИКА","Лабораторная работа","251"),
            Lesson("12:40", "14:10","Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
            Lesson("14:55", "16:25","Авферонок С.Э.", " РАКЕТН. ТЕХНИКА","Лекция","313а"),
        ),  "Ср"),
        Day(listOf(
            Lesson("9:00", "10:30","Крылов В.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
            Lesson("10:50", "12:20","Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
            Lesson("12:40", "14:10","Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лекция","115"),
            Lesson("14:55", "16:25","Крылов В.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
        ),  "Чт",),
        Day(listOf(
            Lesson("10:50", "12:20","Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Лекция","440"),
            Lesson("12:40", "14:10","Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Практическое занятие","440"),
        ),  "Пт",),
        Day(listOf(
            Lesson("9:00", "10:30","Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Лекция","115"),
            Lesson("10:50", "12:20","Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Практическое занятие","251"),
        ),  "Сб"),
        Day(listOf(),  "Вс"),


    ) // дни недели*/

    val coroutineScope = rememberCoroutineScope()

    var day by remember { mutableStateOf(Date()) }

    val statepage = rememberPagerState((day.day -1) + 7*5 )

    if (weekFinalDays.isNotEmpty())
    {
        stateofpager = weekFinalDays[statepage.currentPage%7]
    }

    var prevday: Int
    var nextday: Int


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

        prevday = day.day
        if (prevday == 0) prevday = 7
        day = it
        nextday = day.day
        if (nextday == 0) nextday = 7

        coroutineScope.launch {
            statepage.animateScrollToPage( // анимация при нажатии на день недели календаря
                page = if (prevday < nextday )
                    statepage.currentPage + nextday - prevday
                else
                    statepage.currentPage - prevday + nextday
                )
        }
    },
        selectedDayBackgroundColor = Blueee
    )

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
        count = 7*10,
        state = statepage,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .weight(10f)
            .padding(vertical = 20.dp),
    ) { currentPage ->


        Column(
            modifier = Modifier
                .verticalScroll(
                    state = rememberScrollState()
                )
                .fillMaxSize(),
        ) {
            if (items[currentPage % 14].listOfLessons.isEmpty() && items[currentPage % 14].dayOfWeek == "Сб")

            {
                Column(

                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxSize()
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
            else if (items[currentPage % 14].listOfLessons.isEmpty() && items[currentPage % 14].dayOfWeek == "Вс")
            {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxSize()
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
                for (lesson in items[currentPage % 14].listOfLessons)
                {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        elevation = 15.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .clickable { }
                        ,
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
                                    text = lesson.starttime,
                                    fontSize = 13.sp,
                                    color = Color.LightGray,
                                )
                                Text(
                                    text = lesson.endtime,
                                    fontSize = 13.sp,
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
                                    fontSize = 10.sp,
                                    color = Color.Gray,
                                    fontFamily = FontFamily.SansSerif
                                )
                                Text(
                                    text = lesson.discipline,
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    fontFamily = FontFamily.Cursive
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
                                        fontSize = 10.sp,
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
                                        fontSize = 10.sp,
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
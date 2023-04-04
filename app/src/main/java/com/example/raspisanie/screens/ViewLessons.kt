package com.example.raspisanie.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspisanie.Day
import com.example.raspisanie.Lesson
import com.example.raspisanie.R
import com.example.raspisanie.ui.theme.Blueee
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mrerror.singleRowCalendar.SingleRowCalendar
import com.mrerror.singleRowCalendar.weekFinalDays
import kotlinx.coroutines.launch
import java.util.*

var stateofpager: Date = Date()

@Composable
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
fun ViewLessons()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
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
                Lesson("16:45", "18:15","Галайдин П.А.", "ТОЭ","Лекция","429*"),
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

        HorizontalPager( // новый пейджер дней недели
            count = 7*10,
            state = statepage,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .weight(10f)
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
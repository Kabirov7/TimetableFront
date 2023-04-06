package com.example.raspisanie.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspisanie.Day
import com.example.raspisanie.Lesson
import com.example.raspisanie.DayTime
import com.example.raspisanie.R
import com.example.raspisanie.ui.theme.Blueee
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mrerror.singleRowCalendar.DateUtils
import com.mrerror.singleRowCalendar.SingleRowCalendar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
//import com.mrerror.singleRowCalendar.weekFinalDays
import kotlinx.coroutines.launch
import java.util.*

val calendar = Calendar.getInstance(Locale.getDefault())
var stateofpager: Date = Date()
var weekFinalDays = DateUtils.getFutureDates(
    6,
    Calendar.getInstance().apply {
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        time = calendar.time})

enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.9f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}


@Preview
@Composable
@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@OptIn(ExperimentalPagerApi::class)
fun ViewLessons()
{

    weekFinalDays = DateUtils.getFutureDates(
        6,
        Calendar.getInstance().apply {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            time = calendar.time})

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            //.background(Color.White),
        //verticalArrangement = Arrangement.SpaceBetween
    ) {

        val keys = listOf(
            DayTime("9:00","10:30","1"),
            DayTime("10:50","12:20","2"),
            DayTime("12:40","14:10","3"),
            DayTime("14:10","14:55","|"),
            DayTime("14:55", "16:25","4"),
            DayTime("16:45", "18:15","5"),
            DayTime("18:35","20:05","6"),
        )

        val items = listOf(
            Day(listOf( // четная
                Lesson("14:55","Абсалямова И.А.", "ИН.ЯЗ.","Практическое занятие","309*"),
                Lesson("16:45", "Галайдин П.А.", "ТОЭ","Практическое занятие","507*а"),
            ),  "Пн",),
            Day(listOf(
                Lesson("9:00", "Устиновский Г.С.", "СИСТЕМНОЕ ПО","Лекция","325*"),
                Lesson("10:50", "Трилис А.В.", "ФИЗ.ОСН.МИКРОЭЛ","Лекция","324*"),
                Lesson("12:40", "", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
            ),  "Вт"),
            Day(listOf(
                Lesson("10:50", "Воробьева Е.Е.", "ОСН.СИСТ.АН.","Практическое занятие","259"),
                Lesson("12:40", "Александрова Е.Б", "ВЫСШ.МАТЕМАТ.","Лекция","451"),
                Lesson("14:55", "Живулин В.А.", "ФИЗИКА","Лекция","325"),
                Lesson("16:45", "Галайдин П.А.", "ТОЭ","Лекция","429*"),
            ),  "Ср"),
            Day(listOf(
                Lesson("14:55", "", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
                Lesson("16:45", "Мажайцев Е.А.", "КОМП.ПРАКТИКУМ","Практическое занятие","219*а"),
            ),  "Чт",),
            Day(listOf(
                Lesson("10:50", "Волкова М.В.", "ФИЗ.ОСН.МИКРОЭЛ","Лабораторная работа","430"),
                Lesson("12:40", "Щеглова А.В.", "ВЫСШ.МАТЕМАТ.","Практическое занятие","420*"),
            ),  "Пт",),
            Day(listOf(),  "Сб"),
            Day(listOf(),  "Вс"),


            Day(listOf( // нечетная
                Lesson("12:40", "Устиновский Г.С.", "СИСТЕМНОЕ ПО","Практическое занятие","258*"),
                Lesson("14:55", "Абсалямова И.А.", "ИН.ЯЗ.","Практическое занятие","309*"),
                Lesson("16:45", "Галайдин П.А.", "ТОЭ","Практическое занятие","507*а"),
            ),  "Пн",),
            Day(listOf(
                Lesson("9:00", "Устиновский Г.С.", "СИСТЕМНОЕ ПО","Лекция","325*"),
                Lesson("10:50", "Трилис А.В.", "ФИЗ.ОСН.МИКРОЭЛ","Лекция","324*"),
                Lesson("12:40", "", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
                Lesson("16:45", "Галайдин П.А.", "ТОЭ","Лекция","429*"),
            ),  "Вт"),
            Day(listOf(
                Lesson("10:50", "Дмитриева А.П.", "ПРАВОВЕДЕНИЕ","Практическое занятие","484"),
                Lesson("12:40", "Александрова Е.Б", "ВЫСШ.МАТЕМАТ.","Лекция","451"),
                Lesson("14:55", "Живулин В.А.", "ФИЗИКА","Лекция","325"),
                Lesson("16:45", "Галайдин П.А.", "ТОЭ","Лекция","429*"),
            ),  "Ср"),
            Day(listOf(
                Lesson("10:50", "Степанова О.Ю.", "ПРАВОВЕДЕНИЕ","Лекция","313"),
                Lesson("12:40", "Воробьева Е.Е.", "ОСН.СИСТ.АН.","Лекция","315"),
                Lesson("14:55", "", "ЭК ПО ФК И СПОРТУ","Практическое занятие",""),
                Lesson("16:45", "Мажайцев Е.А.", "КОМП.ПРАКТИКУМ","Практическое занятие","219*а"),
            ),  "Чт",),
            Day(listOf(
                Lesson("12:40", "Щеглова А.В.", "ВЫСШ.МАТЕМАТ.","Практическое занятие","420*"),
                Lesson("14:55", "Лойко А.В.", "ТОЭ","Лабораторная работа","356*"),
                Lesson("16:45", "Мажайцев Е.А.", "КОМП.ПРАКТИКУМ","Практическое занятие","258*")
            ),  "Пт",),
            Day(listOf(), "Сб"),
            Day(listOf(),  "Вс"),
        ) // дни недели

         /*val items = listOf(
             Day(listOf( // четная
                 Lesson("12:40", "Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лабораторная работа","114"),
                 Lesson("14:55", "Гусев А.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
             ),  "Пн",),
             Day(listOf(
                 Lesson("10:50", "Егоров В.В.", "АРТИЛЛ. ТЕХНИКА","Лабораторная работа","СК-1"),
                 Lesson("12:40", "Маслов Д.В.", "АНАЛИЗ КОНСТР. АИУС","Практическое занятие","СК-14"),
                 Lesson("14:55", "Митюшов А.И.", "РАДИОФИЗИКА","Практическое занятие","115"),
                 Lesson("16:45", "Митюшов А.И.", "РАДИОФИЗИКА","Лекция","115"),
             ),  "Вт"),
             Day(listOf(
                 Lesson("10:50", "Перов Л.Д.", "РАДИОФИЗИКА","Лабораторная работа","251"),
                 Lesson("12:40", "Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
                 Lesson("14:55", "Бурковецкий К.А.;Никольченко Ю.А.", " РАКЕТН. ТЕХНИКА","Лабораторная работа","СК21; СК21а"),
             ),  "Ср"),
             Day(listOf(
                 Lesson("9:00", "Гусев А.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
                 Lesson("10:50", "Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
                 Lesson("12:40", "Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лекция","115"),
                 Lesson("14:55", "Гусев А.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
             ),  "Чт",),
             Day(listOf(
                 Lesson("10:50", "Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Лекция","440"),
                 Lesson("12:40", "Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Практическое занятие","440"),
             ),  "Пт",),
             Day(listOf(
                 Lesson("9:00", "Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Лекция","115"),
                 Lesson("10:50", "Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Практическое занятие","251"),
             ),  "Сб"),
             Day(listOf(),  "Вс"),

             Day(listOf( // нечетная
                 Lesson("10:50", "Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Практическое занятие","114"),
                 Lesson("12:40", "Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лабораторная работа","114"),
                 Lesson("14:55", "Крылов В.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
             ),  "Пн",),
             Day(listOf(
                 Lesson("10:50", "Егоров В.В.", "АРТИЛЛ. ТЕХНИКА","Лабораторная работа","СК-1"),
                 Lesson("12:40", "Маслов Д.В.", "АНАЛИЗ КОНСТР. АИУС","Практическое занятие","СК-14"),
                 Lesson("14:55", "Митюшов А.И.", "РАДИОФИЗИКА","Практическое занятие","115"),
                 Lesson("16:45", "Митюшов А.И.", "РАДИОФИЗИКА","Лекция","115"),
             ),  "Вт"),
             Day(listOf(
                 Lesson("10:50", "Перов Л.Д.", "РАДИОФИЗИКА","Лабораторная работа","251"),
                 Lesson("12:40", "Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
                 Lesson("14:55", "Авферонок С.Э.", " РАКЕТН. ТЕХНИКА","Лекция","313а"),
             ),  "Ср"),
             Day(listOf(
                 Lesson("9:00", "Крылов В.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
                 Lesson("10:50", "Оськин И.А. (Егоренков Л.С.)", " ИСТ. ТЕХН. И ВООР","Практическое занятие","440"),
                 Lesson("12:40", "Крылов В.А.", "Т. ОСН. РАДИОТЕХН","Лекция","115"),
                 Lesson("14:55", "Крылов В.А.", "АНАЛИЗ КОНСТР. АИУС","Лабораторная работа","СК-14"),
             ),  "Чт",),
             Day(listOf(
                 Lesson("10:50", "Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Лекция","440"),
                 Lesson("12:40", "Грецова Е.Б.", "ФИЗИКА ГОР. И ВЗР.","Практическое занятие","440"),
             ),  "Пт",),
             Day(listOf(
                 Lesson("9:00", "Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Лекция","115"),
                 Lesson("10:50", "Павлов А.С.", "ЧИСЛ. МЕТ. МОД. пр","Практическое занятие","251"),
             ),  "Сб"),
             Day(listOf(),  "Вс"),


         ) // дни недели*/

        val coroutineScope = rememberCoroutineScope()

        var day by remember { mutableStateOf(Date()) }

        val statepage = rememberPagerState((day.day -1) + 7*5 )

        /*if (weekFinalDays.isNotEmpty())
        {*/
        stateofpager = weekFinalDays[statepage.currentPage%7]
        //}

        var prevday: Int
        var nextday: Int

        SingleRowCalendar(onSelectedDayChange = { // верхний календарь
            prevday = day.day
            if (prevday == 0) prevday = 7
            day = it
            nextday = day.day
            if (nextday == 0) nextday = 7

            if (prevday != nextday)
            coroutineScope.launch {
                statepage.animateScrollToPage( // анимация при нажатии на день недели календаря
                    page = statepage.currentPage + nextday - prevday
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
                    var i = 0
                    var sleeporwindow = false
                    for (lessonkey in keys)
                    {
                        if (i < items[currentPage % 14].listOfLessons.count())
                        {
                            val lesson =  items[currentPage % 14].listOfLessons[i]
                            if (lessonkey.starttime == lesson.starttime)
                            {
                                sleeporwindow = true
                                Card(
                                    shape = RoundedCornerShape(15.dp),
                                    elevation = 15.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                        .clickable {
                                        }
                                        .bounceClick()
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
                                                .background(Blueee)
                                               ,
                                            verticalArrangement = Arrangement.SpaceAround,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = lessonkey.starttime,
                                                fontSize = 13.sp,
                                                color = Color.LightGray,
                                            )
                                            Text(
                                                text = lessonkey.number,
                                                fontSize = 13.sp,
                                                color = Color.Black,
                                                modifier = Modifier
                                                    .align(Alignment.Start)
                                                    .padding(horizontal = 15.dp)
                                                    .drawBehind {
                                                    drawCircle(
                                                        color = Color.White, radius = 25f
                                                    )
                                                        drawLine(color = Color.White,this.size.center.minus(
                                                            Offset(0f,200f)),this.size.center.plus(
                                                            Offset(0f,200f)))
                                                }
                                            )
                                            Text(
                                                text = lessonkey.endtime,
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
                                i++
                            }
                            else
                            {
                                Card(
                                    elevation = 15.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                    ,
                                ) {
                                    if (lessonkey.number != "|")
                                    {
                                        if (sleeporwindow == false)
                                        Image(
                                        painter = painterResource(id = R.drawable.sleep),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        colorFilter = ColorFilter.lighting(Color.LightGray,Color.Gray))
                                        else
                                        Image(
                                            painter = painterResource(id = R.drawable.window),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillWidth,
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            colorFilter = ColorFilter.lighting(Color.Gray,Color.Gray)
                                    )
                                    }
                                    else
                                    {
                                        Image(
                                            painter = painterResource(id = R.drawable.obed),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillWidth,
                                            modifier = Modifier
                                                .fillMaxSize(),
                                        )
                                    }

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
                                                text = lessonkey.starttime,
                                                fontSize = 13.sp,
                                                color = Color.LightGray,
                                            )
                                            Text(
                                                text = lessonkey.number,
                                                fontSize = 13.sp,
                                                color = Color.Black,
                                                modifier = Modifier
                                                    .align(Alignment.Start)
                                                    .padding(horizontal = 15.dp)
                                                    .drawBehind {
                                                        drawCircle(
                                                            color = Color.White, radius = 25f
                                                        )
                                                        drawLine(color = Color.White,this.size.center.minus(
                                                            Offset(0f,200f)),this.size.center.plus(
                                                            Offset(0f,200f)))
                                                    }
                                            )
                                            Text(
                                                text = lessonkey.endtime,
                                                fontSize = 13.sp,
                                                color = Color.LightGray
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        else continue

                    }


                }



            }
        }

    }
}
package com.example.raspisanie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.raspisanie.ui.theme.RaspisanieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var week = Week(
            listOf(
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "06", "Пн", "марта"),
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "07", "Вт", "марта"),
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "08", "Ср", "марта"),
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "09", "Чт", "марта"),
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "10", "Пт", "марта"),
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "11", "Сб", "марта"),
                Day(listOf(Lesson("12:40", "Lolov", "Math")), "12", "Вс", "марта")
            )
        )

        super.onCreate(savedInstanceState)
        setContent {
            ViewWeek(item = week)
        }
    }
}


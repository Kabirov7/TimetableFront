package com.example.raspisanie

import java.time.DayOfWeek
import java.time.Month

data class Week(
    var list: List<Day>,
    var typeOfWeek: String
)

data class Day(
    var listOfLessons: List<Lesson>,
    var date: String,
    var dayOfWeek: String,
    var month: String
)


data class Lesson
    (
    var time: String,
    var teacher: String,
    var discipline: String,
    var typeOfLesson: String
)


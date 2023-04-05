package com.example.raspisanie

import java.time.DayOfWeek
import java.time.Month

data class Week(
    var list: List<Day>,
)

data class Day(
    var listOfLessons: List<Lesson>,
    var dayOfWeek: String,
)

data class LessonNumber
    (
        var starttime: String,
        var endtime: String,
        var number: String,
    )
data class Lesson
    (
    var starttime: String,
    var teacher: String,
    var discipline: String,
    var typeOfLesson: String,
    var auditory: String
)


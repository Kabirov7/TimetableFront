package com.example.raspisanie

data class Week(
    var list: List<Day>,
)

data class Day(
    var listOfLessons: List<Lesson>,
    var dayOfWeek: String,
)

data class DayTime
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


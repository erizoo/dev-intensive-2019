package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    return when (val seconds = TimeUnit.MILLISECONDS.toSeconds(Date().time - this.time)) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд назад"
        in 45..75 -> "минуту назад"
        in 75..45 * 60 -> "${seconds / 60} минут назад"
        in 45 * 60..75 * 60 -> "час назад"
        in 75 * 60..22 * 3600 -> "${seconds / 3600} часов назад"
        in 22 * 3600..26 * 3600 -> "день назад"
        in 26 * 3600..360 * 86400 -> "${seconds / 86400} дней назад"
        else -> "более года назад"
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
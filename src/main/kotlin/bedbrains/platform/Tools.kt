package bedbrains.platform

import bedbrains.shared.datatypes.rules.WeeklyTime
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

object Tools {

    fun clamp(value: Int, min: Int, max: Int): Int {
        return when {
            value < min -> min
            value > max -> max
            else -> value
        }
    }

    fun clamp(value: Double, min: Double, max: Double): Double {
        return when {
            value < min -> min
            value > max -> max
            else -> value
        }
    }

    fun formatTime(hour: Int, minute: Int, locale: Locale): String {
        val dtf = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        val localTime = LocalTime.of(hour, minute)

        return localTime.format(dtf)
    }

    fun formatWeekdayNarrow(day: Int, locale: Locale): String {
        val wf = WeekFields.of(locale)
        val firstDayOfWeek = wf.firstDayOfWeek

        return firstDayOfWeek.plus(day.toLong()).getDisplayName(TextStyle.NARROW, locale)
    }

    fun formatWeekDayFull(day: Int, locale: Locale): String {
        val wf = WeekFields.of(locale)
        val firstDayOfWeek = wf.firstDayOfWeek

        return firstDayOfWeek.plus(day.toLong()).getDisplayName(TextStyle.FULL, locale)
    }

    fun currentWeeklyTime(): WeeklyTime {
        val dateTime = LocalDateTime.now()
        val day = dateTime.dayOfWeek.value
        val hour = dateTime.hour
        val minute = dateTime.minute
        val second = dateTime.second
        val millis = dateTime.nano / 1000000

        return WeeklyTime(day, hour, minute, second, millis)
    }

    fun getFirstWeekDay(): Int {
        val wf = WeekFields.of(Locale.getDefault())
        val firstDayOfWeek = wf.firstDayOfWeek

        return firstDayOfWeek.value
    }
}

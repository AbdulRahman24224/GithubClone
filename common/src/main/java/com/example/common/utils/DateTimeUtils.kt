package com.example.common.utils

import java.util.Calendar
import java.util.Date

object DateTimeUtils {

    fun currentDate(days: Int = 0): Date {
        return Calendar.getInstance().apply {
            set(
                Calendar.DAY_OF_MONTH,
                Calendar.getInstance().get(Calendar.DATE) + days
            )
        }.time
    }

}
package com.example.common.utils.datetime

import java.util.Calendar
import java.util.Date
import javax.inject.Inject


interface DateTimeUtils {

    fun currentDate(days: Int = 0): Date

}

class DateTimeUtilsImpl @Inject constructor() : DateTimeUtils {

    override fun currentDate(days: Int): Date {
        return Calendar.getInstance().apply {
            set(
                Calendar.DAY_OF_MONTH,
                Calendar.getInstance().get(Calendar.DATE) + days
            )
        }.time
    }

}
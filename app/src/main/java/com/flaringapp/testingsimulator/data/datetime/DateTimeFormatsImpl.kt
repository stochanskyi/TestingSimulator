package com.flaringapp.testingsimulator.data.datetime

import java.text.SimpleDateFormat

class DateTimeFormatsImpl : DateTimeFormats {

    override fun formatDateWithTime(time: Long): String {
        return SimpleDateFormat.getDateTimeInstance().format(time)
    }

}

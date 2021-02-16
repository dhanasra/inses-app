package com.inses.utils

import com.inses.data.app.Cdate
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDate(): Cdate {

        val ci = Calendar.getInstance(Locale.ENGLISH).timeInMillis
        val day = SimpleDateFormat("d", Locale.ENGLISH).format(ci)
        val month = SimpleDateFormat("M", Locale.ENGLISH).format(ci)
        val year = SimpleDateFormat("yyyy", Locale.ENGLISH).format(ci)
        val fTime = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(ci)
        return Cdate(day, month, year, ci.toString(), fTime)
    }

    fun getToday():List<String>{
        val gc = GregorianCalendar()
        val date = SimpleDateFormat("d", Locale.ENGLISH).format(gc.timeInMillis)
        val day = SimpleDateFormat("EEE", Locale.ENGLISH).format(gc.timeInMillis)
        return listOf(date,day,gc.timeInMillis.toString())
    }

    fun getTomorrow():List<String>{
        val gc = GregorianCalendar()
        gc.add(Calendar.DATE, 1)
        val date = SimpleDateFormat("d", Locale.ENGLISH).format(gc.timeInMillis)
        val day = SimpleDateFormat("EEE", Locale.ENGLISH).format(gc.timeInMillis)
        return listOf(date,day,gc.timeInMillis.toString())
    }

    fun getDayAfterTomorrow():List<String>{
        val gc = GregorianCalendar()
        gc.add(Calendar.DATE, 2)
        val date = SimpleDateFormat("d", Locale.ENGLISH).format(gc.timeInMillis)
        val day = SimpleDateFormat("EEE", Locale.ENGLISH).format(gc.timeInMillis)
        return listOf(date,day,gc.timeInMillis.toString())
    }


    fun getTimeInMillis():String{
        return Calendar.getInstance().timeInMillis.toString()
    }

    fun getDate(date: String): Cdate {
        val ci = Calendar.getInstance(Locale.ENGLISH).timeInMillis
        val day = SimpleDateFormat("d", Locale.ENGLISH).format(ci)
        val month = SimpleDateFormat("M", Locale.ENGLISH).format(ci)
        val year = SimpleDateFormat("yyyy", Locale.ENGLISH).format(ci)
        val fTime = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(ci)
        return Cdate(day, month, year, ci.toString(), fTime)
    }

    fun getDateTime(date: String): String {
        return SimpleDateFormat("dd/MM hh:mm a", Locale.ENGLISH).format(date.toLong())
    }

    fun getDateFormat(date: String):String{
        return "${date.substring(0, 2)}.${date.substring(2, 4)}.${date.substring(4, 8)}"
    }

    fun getDay(date: String):String{
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
        val dateObject = sdf.parse(
            "${date.substring(0, 2)}.${date.substring(2, 4)}.${
                date.substring(
                    4,
                    8
                )
            }"
        )
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(dateObject)
    }

}
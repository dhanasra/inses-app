package com.inses.ui.dashboard.makeService.steps

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.base.BaseViewModel
import com.inses.ui.dashboard.makeService.MakeServiceNavigator
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Step3ViewModel @Inject constructor(context:Context):BaseViewModel<MakeServiceNavigator>(){

    val ci = Calendar.getInstance(Locale.ENGLISH).time
    val day = SimpleDateFormat("EEEE", Locale.ENGLISH).format(ci)
    var date = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(ci)
    var today = SimpleDateFormat("ddMMyyyy", Locale.ENGLISH).format(ci)
    var time = SimpleDateFormat("hh.mm a", Locale.ENGLISH).format(ci)
    var ch = SimpleDateFormat("HH", Locale.ENGLISH).format(ci)


    var serviceTime = ObservableField("")
    var serviceDate = ObservableField("")
}
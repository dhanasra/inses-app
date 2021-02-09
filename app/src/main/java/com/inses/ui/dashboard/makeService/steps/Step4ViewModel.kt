package com.inses.ui.dashboard.makeService.steps

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.data.BookingData
import com.inses.ui.base.BaseViewModel
import com.inses.ui.dashboard.makeService.MakeServiceNavigator
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Step4ViewModel @Inject constructor(context: Context):BaseViewModel<MakeServiceNavigator>() {

    var discount = ObservableField("")
    var payable = ObservableField("")

}
package com.inses.ui.dashboard.makeService.steps

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.base.BaseViewModel
import com.inses.ui.dashboard.makeService.MakeServiceNavigator
import java.util.*
import javax.inject.Inject

class Step2ViewModel @Inject constructor(context: Context):BaseViewModel<MakeServiceNavigator>() {

    var locality = ObservableField("")
    var no = ObservableField("")
    var street = ObservableField("")
    var phoneNumber = ObservableField("")
}
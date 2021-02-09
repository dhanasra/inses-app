package com.inses.ui.dashboard.makeService.steps

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.base.BaseViewModel
import com.inses.ui.dashboard.makeService.MakeServiceNavigator
import javax.inject.Inject

class PriceViewModel @Inject constructor(context: Context):BaseViewModel<MakeServiceNavigator>() {

    var amount = ObservableField("")
    var noOfService = ObservableField("")
}
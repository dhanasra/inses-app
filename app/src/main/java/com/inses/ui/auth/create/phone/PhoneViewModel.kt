package com.inses.ui.auth.create.phone

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.auth.AuthNavigator
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class PhoneViewModel @Inject constructor(context: Context):BaseViewModel<AuthNavigator>() {

    var phoneNumber = ObservableField("")
}
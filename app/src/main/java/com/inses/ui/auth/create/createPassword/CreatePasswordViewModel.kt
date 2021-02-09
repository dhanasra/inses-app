package com.inses.ui.auth.create.createPassword

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.auth.AuthNavigator
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class CreatePasswordViewModel @Inject constructor(context:Context):BaseViewModel<AuthNavigator>() {

    var password = ObservableField("")
}
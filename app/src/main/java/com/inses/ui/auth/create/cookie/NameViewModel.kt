package com.inses.ui.auth.create.cookie

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.auth.AuthNavigator
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class NameViewModel @Inject constructor(context: Context):BaseViewModel<AuthNavigator>() {

    var firstName = ObservableField("")
    var lastName = ObservableField("")
}
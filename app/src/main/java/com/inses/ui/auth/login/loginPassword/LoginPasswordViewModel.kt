package com.inses.ui.auth.login.loginPassword

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.auth.AuthNavigator
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class LoginPasswordViewModel @Inject constructor(context: Context):BaseViewModel<AuthNavigator>() {

    var password = ObservableField("")
}
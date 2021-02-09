package com.inses.ui.auth.login.loginAuth

import android.content.Context
import androidx.databinding.ObservableField
import com.inses.ui.auth.AuthNavigator
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class LoginAccountViewModel @Inject constructor(context:Context):BaseViewModel<AuthNavigator>(){

    var email = ObservableField("")
}
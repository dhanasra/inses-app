package com.inses.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.inses.Constants
import com.inses.repository.ApiService
import com.inses.ui.base.BaseViewModel
import com.inses.utils.resources.AppPreferenceHelper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(context:Context):BaseViewModel<DashboardNavigator>() {
    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    var phoneNumber = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"")

    var name = pref.getString(AppPreferenceHelper.PREF_KEY_FIRST_NAME,"")
}
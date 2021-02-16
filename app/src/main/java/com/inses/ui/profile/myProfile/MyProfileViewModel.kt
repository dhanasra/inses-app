package com.inses.ui.profile.myProfile

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.inses.Constants
import com.inses.ui.base.BaseViewModel
import com.inses.ui.profile.ProfileNavigator
import com.inses.utils.resources.AppPreferenceHelper
import javax.inject.Inject

class MyProfileViewModel @Inject constructor(context:Context):BaseViewModel<ProfileNavigator>() {
    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    var phoneNumber = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"")

    var name = pref.getString(AppPreferenceHelper.PREF_KEY_FIRST_NAME,"")+" "+pref.getString(AppPreferenceHelper.PREF_KEY_LAST_NAME,"")

    var id = "Customer id: "+FirebaseAuth.getInstance().uid!!.substring(0,10)

    var email =  pref.getString(AppPreferenceHelper.PREF_KEY_EMAIL,"")

}
package com.inses.ui.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(context: Context):BaseViewModel<SplashNavigator>() {

    private var auth = Firebase.auth.currentUser

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    fun defaultSettingsInCache(){
        loginStatus()
    }

    private fun loginStatus(){
        if(auth==null){
            decideNextActivity(false)
        }else{
            decideNextActivity(true)
        }

    }

    private fun decideNextActivity(type:Boolean){
        if(type){
            mResponse.value = "success"
        }else{
            mResponse.value = "failed"
        }
    }
}
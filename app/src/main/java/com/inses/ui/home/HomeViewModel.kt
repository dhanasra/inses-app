package com.inses.ui.home

import android.content.Context
import com.inses.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(context: Context):
    BaseViewModel<HomeNavigator>(){

    val currentScreen = mutableListOf<Screen>()

    enum class Screen{

    }

}
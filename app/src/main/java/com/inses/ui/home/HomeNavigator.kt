package com.inses.ui.home

interface HomeNavigator {

    fun navigateToScreen(screen: HomeViewModel.Screen, vararg params:String?)

}
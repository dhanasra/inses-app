package com.inses.ui.dashboard.makeService

interface MakeServiceNavigator {

    fun navigateToScreen(screen: MakeServiceViewModel.Screen, vararg params:String?)

}
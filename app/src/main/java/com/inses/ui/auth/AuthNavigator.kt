package com.inses.ui.auth

interface AuthNavigator {

    fun navigateToScreen(screen: AuthVIewModel.Screen, vararg params:String?)

}
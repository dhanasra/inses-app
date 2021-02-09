package com.inses.utils

import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.dashboard.makeService.MakeServiceViewModel

sealed class MakeServiceUiEvent {
    class Navigate(val screen: MakeServiceViewModel.Screen, vararg val params: String?): MakeServiceUiEvent()
}

sealed class AuthUiEvent {
    class Navigate(val screen: AuthVIewModel.Screen, vararg val params: String?): AuthUiEvent()
}
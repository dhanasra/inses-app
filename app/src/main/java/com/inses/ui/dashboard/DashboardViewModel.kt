package com.inses.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.inses.repository.ApiService
import com.inses.ui.base.BaseViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(context:Context):BaseViewModel<DashboardNavigator>() {

}
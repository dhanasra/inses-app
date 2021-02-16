package com.inses.ui.bookings.fragments.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inses.ui.base.BaseViewModel
import com.inses.ui.dashboard.DashboardNavigator
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class OrderDetailsViewModel @Inject constructor(context:Context): BaseViewModel<DashboardNavigator>() {


}
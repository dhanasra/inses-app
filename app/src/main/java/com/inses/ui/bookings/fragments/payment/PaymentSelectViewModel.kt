package com.inses.ui.bookings.fragments.payment

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.inses.Constants
import com.inses.data.BookingData
import com.inses.repository.ApiService
import com.inses.ui.base.BaseViewModel
import com.inses.ui.bookings.MyBookingsNavigator
import com.inses.utils.DateUtils
import com.inses.utils.resources.AppPreferenceHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class PaymentSelectViewModel @Inject constructor(context: Context):BaseViewModel<MyBookingsNavigator>(){
    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    var phone = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"8056384773")


    fun updatePayment(bookingData: BookingData,data:String){
        viewModelScope.launch {
            val jsonBody= JSONObject()
            jsonBody.put("transactionData",data)
            jsonBody.put("phone",bookingData.id)
            jsonBody.put("date",bookingData.orderDate)
            val requestBody= RequestBody.create(MediaType.parse("application/json"),jsonBody.toString())
            Log.d("details", DateUtils.getTimeInMillis())
            val getDeferredUserDetails = ApiService.AppApi.retrofitService.updatePayment(requestBody)
            try {
                val details = getDeferredUserDetails.await()
                Log.d("details",details.toString())
                mResponse.value ="success"
            } catch (e: Exception) {
                Log.d("details",e.message.toString())
                mResponse.value = "failed"
            }
        }
    }
}
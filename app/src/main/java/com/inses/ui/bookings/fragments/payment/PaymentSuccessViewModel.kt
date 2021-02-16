package com.inses.ui.bookings.fragments.payment

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inses.data.BookingData
import com.inses.repository.ApiService
import com.inses.ui.base.BaseViewModel
import com.inses.ui.bookings.MyBookingsNavigator
import com.inses.utils.DateUtils
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

class PaymentSuccessViewModel @Inject constructor(context: Context):BaseViewModel<MyBookingsNavigator>() {

    var review = ObservableField("")

    var rating = ObservableField("")

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse


    fun updateReview(bookingData: BookingData){
        viewModelScope.launch {
            if(review.get()!!.toString()==""){
                review.set("empty")
            }
            val jsonBody= JSONObject()
            jsonBody.put("review",review.get()!!.trim().toString())
            jsonBody.put("stars",rating.get()!!.trim().toString())
            jsonBody.put("phone",bookingData.id)
            jsonBody.put("date",bookingData.orderDate)
            val requestBody= RequestBody.create(MediaType.parse("application/json"),jsonBody.toString())
            Log.d("details", DateUtils.getTimeInMillis())
            val getDeferredUserDetails = ApiService.AppApi.retrofitService.updateReview(requestBody)
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
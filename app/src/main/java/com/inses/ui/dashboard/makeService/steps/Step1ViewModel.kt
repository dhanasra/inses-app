package com.inses.ui.dashboard.makeService.steps

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inses.data.Service
import com.inses.repository.ApiService
import com.inses.ui.base.BaseViewModel
import com.inses.ui.dashboard.makeService.MakeServiceNavigator
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class Step1ViewModel @Inject constructor(context: Context):BaseViewModel<MakeServiceNavigator>() {

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    var serviceList = listOf<Service>()

    fun saveToCustomerDetails(service:String){
        viewModelScope.launch {
            val jsonBody= JSONObject()
            jsonBody.put("id",service)

            val requestBody= RequestBody.create(MediaType.parse("application/json"),jsonBody.toString())
            Log.d("details",requestBody.toString())
            val getDeferredUserDetails = ApiService.AppApi.retrofitService.getAllService(requestBody)
            try {
                val details = getDeferredUserDetails.await()
                serviceList = details
                Log.d("details",details.toString())
                mResponse.value ="success"
            } catch (e: Exception) {
                Log.d("details",e.toString())
                mResponse.value = "failed"
            }
        }
    }

}
package com.inses.ui.auth.create

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inses.Constants
import com.inses.repository.ApiService
import com.inses.ui.auth.AuthNavigator
import com.inses.ui.base.BaseViewModel
import com.inses.utils.resources.AppPreferenceHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class CreateLoadingViewModel @Inject constructor(context: Context):BaseViewModel<AuthNavigator>() {

    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    var id = pref.getString(AppPreferenceHelper.PREF_KEY_FIREBASE_USER_ID,"")
    private var customerId = pref.getString(AppPreferenceHelper.PREF_KEY_CUSTOMER_ID,"")
    private var fName = pref.getString(AppPreferenceHelper.PREF_KEY_FIRST_NAME,"")
    private var lName = pref.getString(AppPreferenceHelper.PREF_KEY_LAST_NAME,"")
    var email = pref.getString(AppPreferenceHelper.PREF_KEY_EMAIL,"")
    private var phone = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"")
    private var image = pref.getString(AppPreferenceHelper.PREF_KEY_IMAGE,"")


    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    fun saveToCustomerDetails(){
        viewModelScope.launch {
            val jsonBody= JSONObject()
            jsonBody.put("id",id)
            jsonBody.put("uid",customerId)
            jsonBody.put("fName",fName)
            jsonBody.put("lName",lName)
            jsonBody.put("email",email)
            jsonBody.put("phone",phone)
            jsonBody.put("image",image)

            val requestBody= RequestBody.create(MediaType.parse("application/json"),jsonBody.toString())
            Log.d("details",requestBody.toString())
            val getDeferredUserDetails = ApiService.AppApi.retrofitService.addCustomer(requestBody)
            try {
                val details = getDeferredUserDetails.await()
                Log.d("details",details.toString())
                mResponse.value = details
            } catch (e: Exception) {
                Log.d("details",e.toString())
                mResponse.value = "failed"
            }
        }
    }

}
package com.inses.ui.support.detail

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.inses.Constants
import com.inses.repository.ApiService
import com.inses.ui.base.BaseViewModel
import com.inses.ui.support.SupportNavigator
import com.inses.utils.DateUtils
import com.inses.utils.resources.AppPreferenceHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

class CustomerMessageViewModel @Inject constructor(context: Context) :BaseViewModel<SupportNavigator>() {


    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    var phoneNumber = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"8056384773")

    var name = pref.getString(AppPreferenceHelper.PREF_KEY_FIRST_NAME,"dhana")

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    var message = ObservableField("")
    var date = Calendar.getInstance().timeInMillis.toString()

    fun sendMessage(){
        viewModelScope.launch {
            val jsonBody= JSONObject()
            jsonBody.put("phone",phoneNumber)
            jsonBody.put("date",date)
            jsonBody.put("id", FirebaseAuth.getInstance().uid)
            jsonBody.put("name",name)
            jsonBody.put("message",message.get()!!.trim().toString())
            val requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody.toString())
            Log.d("details", DateUtils.getTimeInMillis())
            val getDeferredUserDetails = ApiService.AppApi.retrofitService.addSupport(requestBody)
            try {
                val details = getDeferredUserDetails.await()
                Log.d("details", details.toString())
                mResponse.value = "success"
            } catch (e: Exception) {
                Log.d("details", e.message.toString())
                mResponse.value = "failed"
            }
        }
    }
}
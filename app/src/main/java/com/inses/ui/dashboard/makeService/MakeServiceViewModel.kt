package com.inses.ui.dashboard.makeService

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
import com.inses.utils.DateUtils.getDate
import com.inses.utils.DateUtils.getTimeInMillis
import com.inses.utils.resources.AppPreferenceHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MakeServiceViewModel @Inject constructor(context: Context) : BaseViewModel<MakeServiceNavigator>() {

    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    var phoneNumber = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"")
    var email = pref.getString(AppPreferenceHelper.PREF_KEY_EMAIL,"")
    var name = """${pref.getString(AppPreferenceHelper.PREF_KEY_FIRST_NAME, "")} ${
        pref.getString(
            AppPreferenceHelper.PREF_KEY_LAST_NAME,
            ""
        )
    }"""

    var serviceName = ObservableField("")
    var perAmount = ObservableField("")
    var image = ObservableField("")
    var type = ObservableField("")
    var amount = ObservableField("")
    var noOfService = ObservableField("")
    var locality = ObservableField("")
    var no = ObservableField("")
    var phone = ObservableField("")
    var area = ObservableField("")
    var nearBy = ObservableField("")
    var street = ObservableField("")
    var serviceDate = ObservableField("")
    var serviceTime = ObservableField("")
    var discount = ObservableField("")
    var payable = ObservableField("")
    var orderDate = ObservableField("")

    enum class Screen{
        PRICE,
        ADDRESS,
        SCHEDULE,
        BOOK,
        FINISH
    }

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    fun makeService(){
        viewModelScope.launch {
            var phoneNumber = pref.getString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"")
            val jsonBody= JSONObject()
            jsonBody.put("id",phoneNumber)
            jsonBody.put("profilePic","empty")
            jsonBody.put("customerId",FirebaseAuth.getInstance().uid)
            jsonBody.put("name",name)
            jsonBody.put("email",email)
            jsonBody.put("phone",phone.get()!!.trim())
            jsonBody.put("serviceName",serviceName.get()!!.trim())
            jsonBody.put("serviceType",type.get()!!.trim())
            jsonBody.put("image",image.get()!!.trim())
            jsonBody.put("no",noOfService.get()!!.trim())
            jsonBody.put("serviceDate",serviceDate.get()!!.trim())
            jsonBody.put("serviceTime",serviceTime.get()!!.trim())
            jsonBody.put("perAmount", perAmount.get()!!.trim())
            jsonBody.put("totalAmount", amount.get()!!.trim())
            jsonBody.put("discount", discount.get()!!.trim())
            jsonBody.put("payable", payable.get()!!.trim())
            jsonBody.put("locality", locality.get()!!.trim())
            jsonBody.put("area", area.get()!!.trim())
            jsonBody.put("nearBy", nearBy.get()!!.trim())
            jsonBody.put("houseNo", no.get()!!.trim())
            jsonBody.put("street", street.get()!!.trim())
            jsonBody.put("orderDate",orderDate.get()!!.trim())
            jsonBody.put("orderTime", getTimeInMillis().toString())
            jsonBody.put("status", "In Progress")
            jsonBody.put("transactionData","empty")
            val requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody.toString())
            Log.d("details", getTimeInMillis())
            val getDeferredUserDetails = ApiService.AppApi.retrofitService.addBooking(requestBody)
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
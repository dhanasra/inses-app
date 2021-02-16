package com.inses.ui.bookings.fragments.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonObject
import com.inses.*
import com.inses.data.BookingData
import com.inses.databinding.ActivityPaymentSelectBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PaymentSelectActivity : BaseActivity<ActivityPaymentSelectBinding,PaymentSelectViewModel>(),PaymentResultWithDataListener {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_payment_select
    override val viewModel: PaymentSelectViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(PaymentSelectViewModel::class.java)
    lateinit var mBinding: ActivityPaymentSelectBinding
    lateinit var bookingData: BookingData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        val booking = intent.extras!!.get("booking") as BookingData
        bookingData = booking
        window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        init(booking)
        initView()
    }

    private fun initView(){
        mBinding.paymentSelectButton.onClick {

            mBinding.paymentSelectButton.visibility = View.GONE
            mBinding.payProgress.visibility = View.VISIBLE

            val checkOut = Checkout()
            checkOut.setKeyID(Constants.RAZOR_PAY_KEY_ID)
            checkOut.setImage(R.drawable.logo)
            //initialize json object
            var jsonObject = JSONObject()
            try {
                //put name
                jsonObject.put("name","Test")
                //put description
                jsonObject.put("description","Test Desc")
                //put theme color
                jsonObject.put("theme.color",Constants.THEME_COLOR)
                //put currency unit
                jsonObject.put("currency","INR")
                //put amount
                jsonObject.put("amount","100")
                //put mobile number
                jsonObject.put("prefill.contact","8056384773")
                //put currency unit
                jsonObject.put("prefill.email","1dhana625@gmail.com")
                //open razorpay activity
                checkOut.open(this,jsonObject)


            }catch (e:JSONException){

            }
        }
    }

    private fun init(bookingData: BookingData){

        subscribeToLiveData()

        mBinding.paymentSelectEpoxyRecyclerView.withModels {
            val orderDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(
                bookingData.orderDate.toLong()
            )
            val orderTime = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                bookingData.orderTime.toLong()
            )
            val payable = bookingData.payable
            val ta = "\u20B9 ${bookingData.totalAmount}"
            val discount = "\u20B9 ${bookingData.discount}"
            imageView {
                id("serviceImage")
                imgUrl(bookingData.image)
            }
            shadowView {
                id("shadow")
            }
            viewholderListingDetailsTitle {
                id("detailsTitle")
                type(bookingData.serviceName)
                title(bookingData.serviceType)
            }
            viewholderListingDetailsSectionHeader {
                id("section1")
                header("Contact")
            }
            viewholderListingDetailsRightLeft {
                id("email")
                leftSide("inses@gmail.com")
            }
            viewholderListingDetailsRightLeft {
                id("leftRight")
                leftSide(Constants.PHONE_NUMBER)
            }
            viewholderListingDetailsSectionHeader {
                id("section4")
                header("Address")
            }
            viewholderListingDetailsRightLeft {
                id("hno")
                leftSide(bookingData.houseNo)
            }
            viewholderListingDetailsRightLeft {
                id("street")
                leftSide(bookingData.street)
            }
            viewholderListingDetailsRightLeft {
                id("area")
                leftSide(bookingData.area)
            }
            viewholderListingDetailsRightLeft {
                id("nearBy")
                leftSide(bookingData.nearBy)
            }
            viewholderListingDetailsRightLeft {
                id("locality")
                leftSide(bookingData.locality)
            }
            viewholderListingDetailsSectionHeader {
                id("section3")
                header("Schedule")
            }
            viewholderListingDetailsRightLeft {
                id("sd")
                leftSide("Service Date")
                rightSide(bookingData.serviceDate)
            }
            viewholderListingDetailsRightLeft {
                id("st")
                leftSide("Service Time")
                rightSide(bookingData.serviceTime)
            }
            viewholderListingDetailsRightLeft {
                id("od")
                leftSide("Order Date")
                rightSide(orderDate)
            }
            viewholderListingDetailsRightLeft {
                id("ot")
                leftSide("Order Time")
                rightSide(orderTime)
            }
            viewholderListingDetailsSectionHeader {
                id("section2")
                header("Payment")
            }
            viewholderListingDetailsRightLeft {
                id("nos")
                leftSide("Number Of Services")
                rightSide(bookingData.no)
            }
            viewholderListingDetailsRightLeft {
                id("tm")
                leftSide("Total Amount")
                rightSide(ta)
            }
            viewholderListingDetailsRightLeft {
                id("discount")
                leftSide("Discount")
                rightSide(discount)
            }
            viewholderListingDetailsRightLeft {
                id("payable")
                leftSide("Payable Amount")
                rightSide(payable)
            }
        }
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(this,{
            if(it=="success"){
                var i = Intent(this,PaymentSuccessActivity::class.java)
                i.putExtra("booking", bookingData)
                startActivity(i)
            }
        })
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {

        viewModel.updatePayment(bookingData,p1!!.orderId+"\n"+p1.paymentId+"\n"+p1.signature+"\n"+p1.userContact+"\n"+p1.userEmail)
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        showSnackBar("Error", "Error $p1 : $p2","")
    }

}
package com.inses.ui.bookings.fragments.details

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.inses.*
import com.inses.data.BookingData
import com.inses.databinding.ActivityOrderDetailsBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.withModels
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OrderDetailsActivity : BaseActivity<ActivityOrderDetailsBinding, OrderDetailsViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_order_details
    override val viewModel: OrderDetailsViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(OrderDetailsViewModel::class.java)
    lateinit var mBinding: ActivityOrderDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        val booking = intent.extras!!.get("booking") as BookingData
        window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        init(booking)
    }

    private fun init(bookingData: BookingData){

        mBinding.status.text = bookingData.status

        when (bookingData.status) {
            "In Progress" -> {
                mBinding.rlStatus.setBackgroundResource(R.drawable.curve_button)
            }
            "Confirmed" -> {
                mBinding.rlStatus.setBackgroundResource(R.drawable.curve_button_two)
            }
            "Completed" -> {
                mBinding.rlStatus.setBackgroundResource(R.drawable.completed_back)
            }
        }

        subscribeToLiveData()

        mBinding.detailsRecyclerView.withModels {
            val location = "${bookingData.houseNo}, ${bookingData.street}, ${bookingData.locality}, "
            val orderDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(
                    bookingData.orderDate.toLong()
            )
            val orderTime = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    bookingData.orderTime.toLong()
            )
            val payable = "${bookingData.payable}"
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

    }

}
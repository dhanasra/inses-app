package com.inses.ui.bookings.fragments.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.inses.*
import com.inses.data.BookingData
import com.inses.databinding.ActivityHistoryDetailsBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.withModels
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HistoryDetailsActivity : BaseActivity<ActivityHistoryDetailsBinding,HistoryDetailsViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_history_details
    override val viewModel: HistoryDetailsViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(HistoryDetailsViewModel::class.java)
    lateinit var mBinding: ActivityHistoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        val booking = intent.extras!!.get("booking") as BookingData
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        init(booking)
    }

    private fun init(bookingData: BookingData) {

        subscribeToLiveData()

        mBinding.detailsRecyclerView.withModels {
            val location =
                "${bookingData.houseNo}, ${bookingData.street}, ${bookingData.locality}, "
            val orderDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(
                bookingData.orderDate.toLong()
            )
            val orderTime = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                bookingData.orderTime.toLong()
            )
            val completedTime = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                bookingData.completedTime.toLong()
            )

            val transactionData = bookingData.transactionData.lines()


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
                id("section4")
                header("Complete")
            }
            viewholderListingDetailsRightLeft {
                id("cd")
                leftSide("Completed Date")
                rightSide(bookingData.completedDate)
            }
            viewholderListingDetailsRightLeft {
                id("ct")
                leftSide("Completed Time")
                rightSide(completedTime)
            }
            viewholderListingDetailsRightLeft {
                id("cby")
                leftSide("Completed By")
                rightSide(bookingData.completedBy)
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
                leftSide("Paid Amount")
                rightSide(payable)
            }
            viewholderListingDetailsRightLeft {
                id("payable")
                leftSide("Transaction Id")
                rightSide(transactionData[1])
            }
            finishedBottom {
                id("finish")
            }
        }
    }

    private fun subscribeToLiveData() {

    }
}
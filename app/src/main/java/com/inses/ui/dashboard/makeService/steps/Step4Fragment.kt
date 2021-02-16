package com.inses.ui.dashboard.makeService.steps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.inses.BR
import com.inses.R
import com.inses.data.BookingData
import com.inses.databinding.FragmentStep4Binding
import com.inses.ui.base.BaseFragment
import com.inses.ui.dashboard.makeService.MakeServiceViewModel
import com.inses.utils.*
import com.inses.viewHolderTitleTextTwo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Step4Fragment(bookingData: BookingData) : BaseFragment<FragmentStep4Binding,Step4ViewModel>() {

    var booking = bookingData

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_step4
    override val viewModel: Step4ViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(Step4ViewModel::class.java)
    private lateinit var mBinding: FragmentStep4Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init()
    }

    private fun init(){

        mBinding.bookNow.onClick {
            mBinding.bookNow.visibility = View.GONE
            mBinding.bookNow.isEnabled = false
            mBinding.bookProgress.visibility = View.VISIBLE
            RxBus.publish(MakeServiceUiEvent.Navigate(MakeServiceViewModel.Screen.FINISH,viewModel.discount.get()!!,viewModel.payable.get()!!, DateUtils.getTimeInMillis().toString()))
        }

        Glide.with(mBinding.img).load(booking.image).into(mBinding.img)
        mBinding.serviceDate.text = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(booking!!.serviceDate.toLong())+", "+booking!!.serviceTime
        mBinding.type.text = booking.serviceType
        mBinding.amount.text = "\u20B9 ${booking.totalAmount}"
        mBinding.no.text = booking.no
        viewModel.discount.set("100")
        viewModel.payable.set("${booking.totalAmount.toInt()-100}")
        mBinding.discount.text = "- \u20B9 100"
        mBinding.address.text = booking!!.houseNo+", "+booking!!.street+", "+booking!!.locality
        mBinding.payableAmount.text = "- \u20B9 ${booking.totalAmount.toInt()-100}"
    }

    private fun setUp() {
        mBinding.scheduleEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("summary")
                text("Summary Of Your Service")
                txtSize("medium")
                txtColor("b")
                back(View.OnClickListener {
                    requireActivity().onBackPressed()
                })
            }
        }
    }
}
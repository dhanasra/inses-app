package com.inses.ui.bookings.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentOngoingBinding
import com.inses.loader
import com.inses.ui.base.BaseFragment
import com.inses.ui.bookings.fragments.details.OrderDetailsActivity
import com.inses.ui.bookings.fragments.payment.PaymentSelectActivity
import com.inses.ui.dashboard.makeService.MakeServiceActivity
import com.inses.ui.home.HomeActivity
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderOnGoingBookings
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OngoingFragment : BaseFragment<FragmentOngoingBinding,OnGoingViewModel>() {
    var isLoading = true
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_ongoing
    override val viewModel: OnGoingViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(OnGoingViewModel::class.java)
    lateinit var mBinding: FragmentOngoingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init()
        subscribeToLiveData()
    }

    private fun init(){
        mBinding.book.onClick {
            mBinding.book.visibility = View.GONE
            mBinding.progressBar.visibility = View.VISIBLE
            startActivity(Intent(requireContext(),HomeActivity::class.java))
        }
        viewModel.makeService()
    }

    private fun setUp(){
        mBinding.ongoingEpoxyRecyclerView.withModels {
            if(isLoading){
                loader {
                    id("loader")
                }
            }else{
                viewModel.bookingList.forEach {
                    val booking = it
                    booking.serviceDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(it.serviceDate.toLong())
                    booking.payable = "\u20B9 ${it.payable}"
                    viewHolderOnGoingBookings {
                        id()
                        booking(booking)
                        onClick(View.OnClickListener {
                            val i = if(booking.status!="Completed"){
                                Intent(requireContext(), OrderDetailsActivity::class.java)
                            }else{
                                Intent(requireContext(), PaymentSelectActivity::class.java)
                            }
                            i.putExtra("booking", booking)
                            startActivity(i)
                        })
                    }
                }
            }
        }
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(requireActivity(),{
            if(it=="added"){
                isLoading = false
                mBinding.ongoingEpoxyRecyclerView.requestModelBuild()
            }else if(it=="empty"){
                mBinding.empty.visibility = View.VISIBLE
            }
        })
    }


}
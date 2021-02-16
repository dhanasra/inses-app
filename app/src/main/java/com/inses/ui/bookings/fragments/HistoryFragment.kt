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
import com.inses.databinding.FragmentHistoryBinding
import com.inses.loader
import com.inses.ui.base.BaseFragment
import com.inses.ui.bookings.fragments.details.OrderDetailsActivity
import com.inses.ui.bookings.fragments.history.HistoryDetailsActivity
import com.inses.ui.bookings.fragments.history.HistoryDetailsViewModel
import com.inses.ui.bookings.fragments.payment.PaymentSelectActivity
import com.inses.ui.home.HomeActivity
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderOnGoingBookings
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HistoryFragment : BaseFragment<FragmentHistoryBinding,HistoryViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_history
    override val viewModel: HistoryViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(HistoryViewModel::class.java)
    lateinit var mBinding: FragmentHistoryBinding
    var isLoading = true
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
        mBinding.historyEpoxyRecyclerView.withModels {
            if(isLoading){
                loader {
                    id("loader")
                }
            }else{
                viewModel.bookingList.forEach {
                    val booking = it
                    booking.serviceDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(it.serviceDate.toLong())
                    booking.completedDate = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).format(it.completedDate.toLong())
                    booking.payable = "\u20B9 ${it.payable}"
                    viewHolderOnGoingBookings {
                        id()
                        booking(booking)
                        onClick(View.OnClickListener {
                            val i = Intent(requireContext(), HistoryDetailsActivity::class.java)
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
                mBinding.historyEpoxyRecyclerView.requestModelBuild()
            }else if(it=="empty"){
                mBinding.empty.visibility = View.VISIBLE
            }
        })
    }


}
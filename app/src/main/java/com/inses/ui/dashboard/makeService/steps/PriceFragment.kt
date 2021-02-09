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
import com.inses.data.Service
import com.inses.databinding.FragmentPriceBinding
import com.inses.ui.base.BaseFragment
import com.inses.ui.dashboard.makeService.MakeServiceViewModel
import com.inses.utils.MakeServiceUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderTitleTextTwo
import javax.inject.Inject

class PriceFragment(ser:Service) : BaseFragment<FragmentPriceBinding,PriceViewModel>() {

    var service = ser

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_price
    override val viewModel: PriceViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(PriceViewModel::class.java)
    lateinit var mBinding: FragmentPriceBinding
    var i =1
    var perAmount = service.price.toInt()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init()
    }

    private fun init(){
        Glide.with(mBinding.serviceImage).load(service.image).into(mBinding.serviceImage)
        mBinding.amountPerService.text = "\u20B9 ${service.price}"
        mBinding.totalAmount.text =  "\u20B9 ${service.price}"
        viewModel.noOfService.set("1")
        viewModel.amount.set(service.price)
        mBinding.authNext.onClick {
            mBinding.authNext.visibility = View.GONE
            mBinding.payProgressBar.visibility = View.VISIBLE
            RxBus.publish(MakeServiceUiEvent.Navigate(MakeServiceViewModel.Screen.ADDRESS,viewModel.amount.get()!!,viewModel.noOfService.get()!!))
        }

        mBinding.add.onClick {
            i++;
            mBinding.totalAmount.text = "\u20B9 ${perAmount*i}"
            viewModel.amount.set("${perAmount*i}")
            viewModel.noOfService.set(i.toString())
            mBinding.no.text  = i.toString()
        }

        mBinding.minus.onClick {
            i--;
            mBinding.totalAmount.text = "\u20B9 ${perAmount*i}"
            viewModel.amount.set("${perAmount*i}")
            viewModel.noOfService.set(i.toString())
            mBinding.no.text  = i.toString()
        }
    }


    private fun setUp() {
        mBinding.priceEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("price")
                text("Amount of service")
                txtSize("medium")
                txtColor("b")
            }


        }


    }

}
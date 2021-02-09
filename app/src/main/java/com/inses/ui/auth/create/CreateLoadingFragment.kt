package com.inses.ui.auth.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentCreateLoadingBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import javax.inject.Inject

class CreateLoadingFragment : BaseFragment<FragmentCreateLoadingBinding,CreateLoadingViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_create_loading
    override val viewModel: CreateLoadingViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(CreateLoadingViewModel::class.java)
    private lateinit var mBinding: FragmentCreateLoadingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
        subscribeToLiveData()
    }

    private fun initView(){
        viewModel.saveToCustomerDetails()
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(requireActivity(), {
            if(it=="success"){
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.HOME,"home"))
            }
        })
    }

}
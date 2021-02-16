package com.inses.ui.auth.create.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentPhoneBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import javax.inject.Inject

class PhoneFragment : BaseFragment<FragmentPhoneBinding,PhoneViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_phone
    override val viewModel: PhoneViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(PhoneViewModel::class.java)
    lateinit var mBinding: FragmentPhoneBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        mBinding.done.visibility = View.VISIBLE
        mBinding.doneProgress.visibility = View.GONE
        mBinding.done.onClick {
            mBinding.done.visibility = View.GONE
            mBinding.doneProgress.visibility = View.VISIBLE
            if(viewModel.phoneNumber.get()!!.trim().isEmpty()){
                mBinding.done.visibility = View.VISIBLE
                mBinding.doneProgress.visibility = View.GONE
                hideKeyboard()
                showSnackBar("Invalid","Enter your Phone Number","")
            }else if(viewModel.phoneNumber.get()!!.length!=10){
                mBinding.done.visibility = View.VISIBLE
                mBinding.doneProgress.visibility = View.GONE
                hideKeyboard()
                showSnackBar("Invalid","Phone number is not valid","")
            }else {
                hideKeyboard()
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.C_LOADING,viewModel.phoneNumber.get()!!.trim()))
            }
        }
    }


}
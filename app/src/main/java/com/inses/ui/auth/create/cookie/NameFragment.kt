package com.inses.ui.auth.create.cookie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentNameBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import javax.inject.Inject

class NameFragment : BaseFragment<FragmentNameBinding,NameViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_name
    override val viewModel: NameViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(NameViewModel::class.java)
    private lateinit var mBinding: FragmentNameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        mBinding.finish.visibility = View.VISIBLE
        mBinding.finishProgress.visibility = View.GONE
        mBinding.finish.onClick {
            mBinding.finish.visibility = View.GONE
            mBinding.finishProgress.visibility = View.VISIBLE
            if(viewModel.firstName.get()!!.trim().isEmpty()){
                mBinding.finish.visibility = View.VISIBLE
                mBinding.finishProgress.visibility = View.GONE
                hideKeyboard()
                showSnackBar("Invalid","First name should not be empty","")
            }else if(viewModel.lastName.get()!!.trim().isEmpty()){
                mBinding.finish.visibility = View.VISIBLE
                mBinding.finishProgress.visibility = View.GONE
                hideKeyboard()
                showSnackBar("Invalid","Last name should not be empty","")
            }else {
                hideKeyboard()
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.C_PHONE,viewModel.firstName.get()!!.trim(),viewModel.lastName.get()!!.trim()))
            }
        }
    }

}
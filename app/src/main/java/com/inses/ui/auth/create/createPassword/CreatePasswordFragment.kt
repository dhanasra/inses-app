package com.inses.ui.auth.create.createPassword

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentCreatePasswordBinding
import com.inses.databinding.FragmentLoginPasswordBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import javax.inject.Inject

class CreatePasswordFragment : BaseFragment<FragmentCreatePasswordBinding,CreatePasswordViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_create_password
    override val viewModel: CreatePasswordViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(CreatePasswordViewModel::class.java)
    lateinit var mBinding: FragmentCreatePasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
    }

    private fun initView(){
        mBinding.createProgress.visibility = View.GONE
        mBinding.create.visibility = View.VISIBLE
        mBinding.back.onClick {
            activity?.onBackPressed()
        }
        mBinding.create.onClick {
            mBinding.createProgress.visibility = View.VISIBLE
            mBinding.create.visibility = View.GONE
            if(viewModel.password.get()!!.isEmpty()){
                hideKeyboard()
                mBinding.createProgress.visibility = View.GONE
                mBinding.create.visibility = View.VISIBLE
                showSnackBar("Invalid","Password should not be empty","")
            }else if(viewModel.password.get()!!.length<7){
                hideKeyboard()
                mBinding.createProgress.visibility = View.GONE
                mBinding.create.visibility = View.VISIBLE
                showSnackBar("Invalid","Password should contain more than 7 letters","")
            }else{
                hideKeyboard()
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.NAME,viewModel.password.get()!!.trim()))
            }
        }
    }

}
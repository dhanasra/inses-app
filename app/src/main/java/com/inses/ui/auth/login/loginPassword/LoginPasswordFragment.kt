package com.inses.ui.auth.login.loginPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentLoginPasswordBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import javax.inject.Inject

class LoginPasswordFragment : BaseFragment<FragmentLoginPasswordBinding,LoginPasswordViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_login_password
    override val viewModel: LoginPasswordViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(LoginPasswordViewModel::class.java)
    lateinit var mBinding: FragmentLoginPasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
    }

    private fun initView(){
        mBinding.loginProgress.visibility = View.GONE
        mBinding.login.visibility = View.VISIBLE
        mBinding.back.onClick {
            activity?.onBackPressed()
        }
        mBinding.login.onClick {
            mBinding.loginProgress.visibility = View.VISIBLE
            mBinding.login.visibility = View.GONE
            if(viewModel.password.get()!!.isEmpty()){
                hideKeyboard()
                mBinding.loginProgress.visibility = View.GONE
                mBinding.login.visibility = View.VISIBLE
                showSnackBar("Invalid","Password should not be empty","")
            }else if(viewModel.password.get()!!.length<7){
                hideKeyboard()
                mBinding.loginProgress.visibility = View.GONE
                mBinding.login.visibility = View.VISIBLE
                showSnackBar("Invalid","Password should contain more than 7 letters","")
            }else{
                hideKeyboard()
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.L_HOME,viewModel.password.get()!!.trim()))
            }
        }
    }


}
package com.inses.ui.auth.create.createAuth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentCreateAccountBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import javax.inject.Inject

class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding,CreateAccountViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_create_account
    override val viewModel: CreateAccountViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(CreateAccountViewModel::class.java)
    private lateinit var mBinding: FragmentCreateAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
    }

    private fun initView(){
        mBinding.loginwithEmail.setOnClickListener {
            loginWithEmail()
        }
        mBinding.loginWithNumber.setOnClickListener {
            loginWithNumber()
        }
        mBinding.goToLogin.onClick {
            hideKeyboard()
            RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.LOGIN,"create"))
        }
        mBinding.googleSignin.onClick {
            RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.GOOGLE,"google"))
        }
        mBinding.emailButton.onClick {
            if(viewModel.email.get()!!.isEmpty()){
                hideKeyboard()
                showSnackBar("Invalid","Email should not be empty","")
            }else if(!Patterns.EMAIL_ADDRESS.matcher(viewModel.email.get()!!).matches()){
                hideKeyboard()
                showSnackBar("Invalid","Email address is not valid","")
            }else{
                hideKeyboard()
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.C_PASSWORD,viewModel.email.get()!!.trim()))
            }
        }
    }

    fun loginWithEmail(){
        mBinding.loginwithEmail.visibility= View.GONE
        mBinding.loginWithNumber.visibility= View.VISIBLE
        mBinding.emailLayout.visibility= View.VISIBLE
        mBinding.emailButton.visibility= View.VISIBLE
        mBinding.numberButton.visibility= View.GONE
        mBinding.numberLayout.visibility= View.GONE
    }

    fun loginWithNumber(){
        mBinding.loginwithEmail.visibility= View.VISIBLE
        mBinding.loginWithNumber.visibility= View.GONE
        mBinding.emailLayout.visibility= View.GONE
        mBinding.numberLayout.visibility= View.VISIBLE
        mBinding.emailButton.visibility= View.GONE
        mBinding.numberButton.visibility= View.VISIBLE
    }

}
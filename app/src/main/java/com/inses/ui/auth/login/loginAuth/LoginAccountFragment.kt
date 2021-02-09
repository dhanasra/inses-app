package com.inses.ui.auth.login.loginAuth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentLoginAccountBinding
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.base.BaseFragment
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import javax.inject.Inject

class LoginAccountFragment : BaseFragment<FragmentLoginAccountBinding,LoginAccountViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_login_account
    override val viewModel: LoginAccountViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(LoginAccountViewModel::class.java)
    private lateinit var mBinding: FragmentLoginAccountBinding

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
        mBinding.goToRegister.onClick {
            hideKeyboard()
            RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.CREATE,"create"))
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
                RxBus.publish(AuthUiEvent.Navigate(AuthVIewModel.Screen.L_PASSWORD,viewModel.email.get()!!.trim()))
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
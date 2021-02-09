package com.inses.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivitySplashBinding
import com.inses.ui.auth.AuthActivity
import com.inses.ui.base.BaseActivity
import com.inses.ui.home.HomeActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(),SplashNavigator {
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(SplashViewModel::class.java)
    private lateinit var mBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        Handler().postDelayed({
            viewModel.defaultSettingsInCache()
        },2000)

        subscribeToLiveData()
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(this,{
            when(it){
                "success"->{
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                }
                "failed"->{
                    startActivity(Intent(this,AuthActivity::class.java))
                    finish()
                }
            }
        })
    }

}
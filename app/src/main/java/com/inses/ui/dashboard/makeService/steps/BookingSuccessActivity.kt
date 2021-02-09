package com.inses.ui.dashboard.makeService.steps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.databinding.BR
import com.inses.R
import com.inses.databinding.ActivityBookingSuccessBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.onClick
import javax.inject.Inject

class BookingSuccessActivity : BaseActivity<ActivityBookingSuccessBinding,Step4ViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_booking_success
    override val viewModel: Step4ViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(Step4ViewModel::class.java)
    lateinit var mBinding: ActivityBookingSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        init()
    }

    private fun init(){
        mBinding.backToHome.onClick {
            mBinding.backToHome.visibility = View.GONE
            mBinding.bookProgress.visibility = View.VISIBLE
            onBackPressed()
        }
    }

}
package com.inses.ui.bookings.fragments.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.data.BookingData
import com.inses.databinding.ActivityPaymentSuccessBinding
import com.inses.ui.base.BaseActivity
import com.inses.ui.home.HomeActivity
import com.inses.utils.onClick
import javax.inject.Inject

class PaymentSuccessActivity : BaseActivity<ActivityPaymentSuccessBinding,PaymentSuccessViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_payment_success
    override val viewModel: PaymentSuccessViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(PaymentSuccessViewModel::class.java)
    lateinit var mBinding: ActivityPaymentSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        val booking = intent.extras!!.get("booking") as BookingData
        init(booking)
        subscribeToLiveData()
    }

    private fun init(bookingData: BookingData){
        mBinding.done.onClick {
            mBinding.done.visibility = View.GONE
            mBinding.progress.visibility = View.VISIBLE

            viewModel.rating.set(mBinding.rb.rating.toString())
            viewModel.updateReview(bookingData)
        }
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(this,{
            if(it=="success"){
                startActivity(Intent(this,HomeActivity::class.java))
                finishAffinity()
            }else{
                showSnackBar("Error","Could not upload Review","")
            }
        })
    }

}
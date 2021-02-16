package com.inses.ui.support.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivityCustomerMessageBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderTitleTextTwo
import com.inses.viewHolderViewBold
import javax.inject.Inject

class CustomerMessageActivity : BaseActivity<ActivityCustomerMessageBinding,CustomerMessageViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_customer_message
    override val viewModel: CustomerMessageViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(CustomerMessageViewModel::class.java)
    private lateinit var mBinding:ActivityCustomerMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init()
        subscribeToLiveData()
    }

    private fun init(){
        mBinding.report.onClick {
            if(viewModel.message.get()!!.trim().isNotEmpty()) {
                mBinding.report.visibility = View.GONE
                mBinding.progressBar.visibility = View.VISIBLE
                viewModel.sendMessage()
            }else{
                showSnackBar("Invalid","Type a message to send","")
            }
        }
    }

    private fun setUp(){
        mBinding.customerMessageEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("customerMessage")
                text("Customer Support & Help")
                txtSize("medium")
                txtColor("b")
            }
            viewHolderViewBold {
                id("bold")
            }
        }
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(this,{
            if(it=="success"){
                showSnackBar("Success","Successfully reported your issue","")
                mBinding.report.visibility = View.VISIBLE
                mBinding.progressBar.visibility = View.GONE
                mBinding.de.setText("")
            }
        })
    }
}
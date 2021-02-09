package com.inses.ui.support.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivityCustomerMessageBinding
import com.inses.ui.base.BaseActivity
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
}
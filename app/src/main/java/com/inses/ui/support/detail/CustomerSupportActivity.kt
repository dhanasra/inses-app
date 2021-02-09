package com.inses.ui.support.detail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivityCustomerSupportBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderTitleTextTwo
import com.inses.viewHolderViewBold
import javax.inject.Inject

class CustomerSupportActivity : BaseActivity<ActivityCustomerSupportBinding,CustomerSupportViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_customer_support
    override val viewModel: CustomerSupportViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(CustomerSupportViewModel::class.java)
    private lateinit var mBinding: ActivityCustomerSupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init()
    }

    private fun init(){
        mBinding.message.onClick {
            startActivity(Intent(this,CustomerMessageActivity::class.java))
        }
        mBinding.call.onClick {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"8056384773")))
        }
    }

    private fun setUp(){
        mBinding.customerSupportEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("customerSupport")
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
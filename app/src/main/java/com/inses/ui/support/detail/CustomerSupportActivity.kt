package com.inses.ui.support.detail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.inses.*
import com.inses.databinding.ActivityCustomerSupportBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.onClick
import com.inses.utils.withModels
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
        val type = intent.extras!!.getString("type")

        initView(type!!)
    }

    private fun initView(type:String){
        if(type=="one"){
            mBinding.qn.text = getString(R.string.support1_title)
            mBinding.an.text = getString(R.string.support1_desc)
        }else if(type=="two"){
            mBinding.qn.text = getString(R.string.support2_title)
            mBinding.an.text = getString(R.string.support2_desc)
        }else if(type=="three"){
            mBinding.qn.text = getString(R.string.support3_title)
            mBinding.an.text = getString(R.string.support3_desc)
        }
    }

    private fun init(){

        mBinding.message.onClick {
            startActivity(Intent(this,CustomerMessageActivity::class.java))
        }
        mBinding.call.onClick {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Constants.PHONE_NUMBER)))
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
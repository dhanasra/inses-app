package com.inses.ui.profile.myProfile

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivityMyProfileBinding
import com.inses.ui.base.BaseActivity
import javax.inject.Inject

class MyProfileActivity : BaseActivity<ActivityMyProfileBinding,MyProfileViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_my_profile
    override val viewModel: MyProfileViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(MyProfileViewModel::class.java)
    private lateinit var mBinding:ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
    }

}
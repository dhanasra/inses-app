package com.inses.ui.profile.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inses.*
import com.inses.databinding.ActivityAboutUsBinding
import com.inses.ui.base.BaseActivity
import com.inses.utils.withModels
import javax.inject.Inject

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding,AboutUsViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_about_us
    override val viewModel: AboutUsViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(AboutUsViewModel::class.java)
    private lateinit var mBinding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
    }

    private fun setUp(){
        mBinding.aboutUsEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("aboutUs")
                text("About Us")
                txtSize("medium")
                txtColor("b")
            }
            viewHolderViewBold {
                id("bold")
            }
            viewHolderTextBold {
                id("about")
                text("INSES")
            }
            viewHolderTextAlone {
                id("one")
                text(getString(R.string.about))
            }
            viewHolderTextBold {
                id("vision")
                text("VISION")
            }
            viewHolderTextAlone {
                id("three")
                text(getString(R.string.vision))
            }
            viewHolderTextBold {
                id("mission")
                text("MISSION")
            }
            viewHolderTextAlone {
                id("two")
                text(getString(R.string.mission))
            }
            viewHolderTextBold {
                id("industries")
                text("Industries We Serve")
            }
            viewHolderTextAlone {
                id("four")
                text(getString(R.string.industries))
            }
            viewHolderTextBold {
                id("category")
                text("Business Categories")
            }
            viewHolderTextAlone {
                id("five")
                text(getString(R.string.bussinessCategories))
            }
        }
    }

}
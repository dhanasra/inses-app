package com.inses.ui.support

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.inses.*
import com.inses.databinding.FragmentSupportBinding
import com.inses.ui.base.BaseFragment
import com.inses.ui.support.detail.CustomerMessageActivity
import com.inses.ui.support.detail.CustomerSupportActivity
import com.inses.utils.withModels
import javax.inject.Inject

class SupportFragment : BaseFragment<FragmentSupportBinding, SupportViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_support
    override val viewModel: SupportViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(SupportViewModel::class.java)
    private lateinit var mBinding: FragmentSupportBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
    }

    private fun setUp(){
        mBinding.supportRecyclerView.withModels {
            viewHolderSubtitle {
                id("support")
                text("Support & Help")
                txtSize("medium")
                txtColor("b")
            }
            viewHolderViewBold {
                id("bold")
            }
            viewHolderTextAlone {
                id("su1")
                onClick(View.OnClickListener {
                    startActivity(Intent(requireContext(),CustomerSupportActivity::class.java))
                })
                text("How can I book a service online?")
            }
            viewHolderLight {
                id("light1")
            }
            viewHolderTextAlone {
                id("su2")
                text("Can I cancel or reschedule my service?")
            }
            viewHolderLight {
                id("light2")
            }
            viewHolderTextAlone {
                id("su3")
                text("How to contact INSES partner for my booking?")
            }
            viewHolderLight {
                id("light3")
            }
            viewHolderTextAlone {
                id("su4")
                text("What happens if I am not satisfied with the quality of service?")
            }
            viewHolderLight {
                id("light4")
            }
        }
    }


}
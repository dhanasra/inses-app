package com.inses.ui.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.databinding.BR
import com.inses.*
import com.inses.databinding.FragmentDashboardBinding
import com.inses.ui.base.BaseFragment
import com.inses.ui.dashboard.makeService.MakeServiceActivity
import com.inses.utils.withModels
import javax.inject.Inject

class DashboardFragment : BaseFragment<FragmentDashboardBinding,DashboardViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_dashboard
    override val viewModel: DashboardViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(DashboardViewModel::class.java)
    lateinit var mBinding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
    }

    private fun setUp(){
        var i = Intent(requireContext(),MakeServiceActivity::class.java)
        mBinding.dashboardEpoxyRecyclerView.withModels {
            viewHolderAppName {
                id("app name")
                spanSizeOverride{_,_,_->2}
            }
            viewHolderTitleText {
                id("title")
                text("Hello")
                text2("Dhana!")
                tvVisibility(true)
                txtSize("medium")
                txtColor("b")
                txtStyle("b")
                isCenter(false)
                ivVisibility(true)
                spanSizeOverride{_,_,_->2}
            }
            viewHolderCall {
                id("call")
                onClick(View.OnClickListener {
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"8825862159")))
                })
                spanSizeOverride{_,_,_->2}
            }
            viewHolderSearch {
                id("search")
                onClick(View.OnClickListener {

                })
                spanSizeOverride{_,_,_->2}
            }
            viewHolderSubtitle {
                id("services")
                text("Services")
                txtSize("medium")
                txtColor("b")
                imageType("w")
                spanSizeOverride{_,_,_->2}
            }
            viewHolderBoxItem {
                id("plumbing")
                text("Plumbing")
                txtSize("smaller")
                setBackground(true)
                imageType("pl")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","Plumbing")
                    startActivity(i)
                })
            }
            viewHolderBoxItem {
                id("painting")
                text("Painting")
                txtSize("smaller")
                setBackground(true)
                imageType("pa")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","Painting")
                    startActivity(i)
                })
            }
            viewHolderBoxItem {
                id("maintenance")
                text("Maintenance")
                txtSize("smaller")
                setBackground(true)
                imageType("m")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","Maintanance")
                    startActivity(i)
                })
            }
            viewHolderBoxItem {
                id("led")
                text("LED tv")
                txtSize("smaller")
                setBackground(true)
                imageType("led")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","LED TV")
                    startActivity(i)
                })
            }
            viewHolderBoxItem {
                id("nc")
                text("New Connection")
                txtSize("smaller")
                setBackground(true)
                imageType("bw")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","New Connection")
                    startActivity(i)
                })
            }
            viewHolderBoxItem {
                id("electrician")
                text("Electricals")
                txtSize("smaller")
                setBackground(true)
                imageType("e")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","Electrical works")
                    startActivity(i)
                })
            }
            viewHolderBoxItem {
                id("installation")
                text("Installation")
                txtSize("smaller")
                setBackground(true)
                imageType("i")
                txtColor("b")
                onClick(View.OnClickListener {
                    i.putExtra("service","Installation")
                    startActivity(i)
                })
            }
        }
    }


}
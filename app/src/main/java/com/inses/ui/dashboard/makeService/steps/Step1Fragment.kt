package com.inses.ui.dashboard.makeService.steps

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.inses.*
import com.inses.databinding.FragmentStep1Binding
import com.inses.ui.base.BaseFragment
import com.inses.ui.dashboard.makeService.MakeServiceViewModel
import com.inses.utils.MakeServiceUiEvent
import com.inses.utils.RxBus
import com.inses.utils.withModels
import javax.inject.Inject

class Step1Fragment(service:String) : BaseFragment<FragmentStep1Binding,Step1ViewModel>() {

    var serviceType = service
    var isLoading = true
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_step1
    override val viewModel: Step1ViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(Step1ViewModel::class.java)
    private lateinit var mBinding: FragmentStep1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        subscribeToLiveData()
        init()
    }

    private fun init(){
        viewModel.saveToCustomerDetails(serviceType)
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(requireActivity(),{
            if(it=="success"){
                isLoading = false
                mBinding.categoryEpoxyRecyclerView.requestModelBuild()
            }
        })
    }

    private fun setUp(){
        mBinding.categoryEpoxyRecyclerView.withModels {
            viewHolderSubtitle {
                id("category")
                text("Select Category")
                txtSize("medium")
                txtColor("b")
            }
            if(isLoading){
                loader {
                    id("loader")
                }
            }else {
                viewModel.serviceList.forEach {
                    var service = it
                    viewHolderCategoryListItem {
                        id(it.type)
                        text(it.type)
                        txtColor("b")
                        txtSize("smaller")
                        setBackground(true)
                        imageType(it.image)
                        onClick(View.OnClickListener {
                            RxBus.publish(MakeServiceUiEvent.Navigate(MakeServiceViewModel.Screen.PRICE,service.service,service.image,service.type,service.price))
                        })
                    }
                }
            }
        }
    }

}
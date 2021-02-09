package com.inses.ui.dashboard.makeService

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.data.BookingData
import com.inses.data.Service
import com.inses.databinding.ActivityMakeServiceBinding
import com.inses.ui.base.BaseActivity
import com.inses.ui.dashboard.makeService.steps.*
import com.inses.utils.MakeServiceUiEvent
import com.inses.utils.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MakeServiceActivity : BaseActivity<ActivityMakeServiceBinding,MakeServiceViewModel>(),MakeServiceNavigator {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_make_service
    override val viewModel: MakeServiceViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(MakeServiceViewModel::class.java)
    private lateinit var mBinding:ActivityMakeServiceBinding
    private val activity = this
    private var eventCompositeDisposal = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
        var service = intent.extras!!.getString("service")
        entryPoint(savedInstanceState,service!!)
        subscribeToLiveData()
        initRxBusListener()

    }

    private fun initView(){

    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(this,{
            if(it=="success"){
                startActivity(Intent(this,BookingSuccessActivity::class.java))
                finish()
            }
        })
    }

    private fun initRxBusListener() {
        eventCompositeDisposal.add(
            RxBus.listen(MakeServiceUiEvent.Navigate::class.java)
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .doOnNext { hideSnackBar() }
                .subscribe { navigateToScreen(it.screen, *it.params) })
    }

    private fun entryPoint(savedInstanceState: Bundle?,service:String){
        if(savedInstanceState==null){
            val fragment = Step1Fragment(service)
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction().add(mBinding.flMakeService.id,fragment).commitAllowingStateLoss()
        }
    }

    override fun navigateToScreen(screen: MakeServiceViewModel.Screen, vararg params: String?) {
        when(screen){
            MakeServiceViewModel.Screen.PRICE -> moveToPriceFragment(params[0]!!,params[1]!!,params[2]!!,params[3]!!)
            MakeServiceViewModel.Screen.ADDRESS -> moveToAddressFragment(params[0]!!,params[1]!!)
            MakeServiceViewModel.Screen.SCHEDULE -> moveToScheduleFragment(params[0]!!,params[1]!!,params[2]!!,params[3]!!)
            MakeServiceViewModel.Screen.BOOK -> moveToBookFragment(params[0]!!,params[1]!!)
        MakeServiceViewModel.Screen.FINISH -> moveToFinishFragment(params[0]!!,params[1]!!,params[2]!!)
        }
    }

    private fun moveToPriceFragment(serviceName:String,serviceImage:String,serviceType:String,serviceCost:String){
        viewModel.serviceName.set(serviceName)
        viewModel.image.set(serviceImage)
        viewModel.perAmount.set(serviceCost)
        viewModel.type.set(serviceType)
        val fragment = PriceFragment(Service(serviceImage,serviceCost,serviceName,serviceType))
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flMakeService.id,fragment).addToBackStack("step2").commitAllowingStateLoss()
    }


    private fun moveToAddressFragment(amount:String,noOfService:String){
        viewModel.noOfService.set(noOfService)
        viewModel.amount.set(amount)
        val fragment = Step2Fragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flMakeService.id,fragment).addToBackStack("step2").commitAllowingStateLoss()
    }

    private fun moveToScheduleFragment(locality:String,no:String,street:String,phone:String){
        viewModel.locality.set(locality)
        viewModel.no.set(no)
        viewModel.street.set(street)
        viewModel.phone.set(phone)
        val fragment = Step3Fragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flMakeService.id,fragment).addToBackStack("step3").commitAllowingStateLoss()
    }

    private fun moveToBookFragment(serviceDate:String,serviceTime:String){
        viewModel.serviceDate.set(serviceDate)
        viewModel.serviceTime.set(serviceTime)

        val booking = BookingData(
            viewModel.phoneNumber!!,
            viewModel.serviceName.get()!!,
            viewModel.type.get()!!,
            viewModel.image.get()!!,
            viewModel.noOfService.get()!!,
            viewModel.perAmount.get()!!,
            viewModel.amount.get()!!,
            viewModel.discount.get()!!,
            viewModel.payable.get()!!,
            viewModel.serviceDate.get()!!,
            viewModel.serviceTime.get()!!,
            viewModel.phone.get()!!,
            viewModel.locality.get()!!,
            viewModel.no.get()!!,
            viewModel.street.get()!!,
            "","","",false
        )

        val fragment = Step4Fragment(booking)
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flMakeService.id,fragment).addToBackStack("step4").commitAllowingStateLoss()
    }

    private fun moveToFinishFragment(discount:String,payable:String,orderDate:String){
        viewModel.discount.set(discount)
        viewModel.payable.set(payable)
        viewModel.orderDate.set(orderDate)
        viewModel.makeService()
    }
}
package com.inses.ui.home

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivityHomeBinding
import com.inses.ui.base.BaseActivity
import com.inses.ui.bookings.MyBookingsFragment
import com.inses.ui.dashboard.DashboardFragment
import com.inses.ui.profile.ProfileFragment
import com.inses.ui.support.SupportFragment
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
    private lateinit var mBinding: ActivityHomeBinding
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
        entryPoint(savedInstanceState)
    }

    private fun initView() {
        setUpBottomNavigation()
    }

    private fun entryPoint(savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            val fragment = DashboardFragment()
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction().add(mBinding.flHome.id,fragment).commitAllowingStateLoss()
        }
    }


    private fun setUpBottomNavigation() {
        with(mBinding.bottomNavigation) {
            val item1 = AHBottomNavigationItem("Home", R.drawable.ic_home)
            val item2 = AHBottomNavigationItem("My Bookings", R.drawable.ic_calendar)
            val item3 = AHBottomNavigationItem("Support", R.drawable.ic_help)
            val item4 = AHBottomNavigationItem("Profile", R.drawable.ic_user)
            mBinding.bottomNavigation.addItem(item1)
            mBinding.bottomNavigation.addItem(item2)
            mBinding.bottomNavigation.addItem(item3)
            mBinding.bottomNavigation.addItem(item4)
            mBinding.bottomNavigation.titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE
            accentColor = ContextCompat.getColor(context!!, R.color.app_button)
            inactiveColor = ContextCompat.getColor(context!!, R.color.grey)
            isColored = false
            titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE
        }
        mBinding.bottomNavigation.setOnTabSelectedListener { position, _ ->
            when(position){
                0 -> { moveToDashboardFragment() }
                1 -> { moveToMyBookingsFragment() }
                2 -> { moveToSupportFragment() }
                3 -> { moveToProfileFragment() }
            }
            true
        }
        mBinding.bottomNavigation.setOnNavigationPositionListener {

        }
    }



    override fun navigateToScreen(screen: HomeViewModel.Screen, vararg params: String?) {

    }

    private fun moveToDashboardFragment(){
        val fragment = DashboardFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flHome.id,fragment).commitAllowingStateLoss()
    }

    private fun moveToMyBookingsFragment(){
        val fragment = MyBookingsFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flHome.id,fragment).commitAllowingStateLoss()
    }

    private fun moveToSupportFragment(){
        val fragment = SupportFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flHome.id,fragment).commitAllowingStateLoss()
    }

    private fun moveToProfileFragment(){
        val fragment = ProfileFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flHome.id,fragment).commitAllowingStateLoss()
    }
}
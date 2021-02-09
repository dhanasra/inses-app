package com.inses.ui.bookings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentMyBookingsBinding
import com.inses.ui.base.BaseFragment
import com.inses.ui.bookings.fragments.HistoryFragment
import com.inses.ui.bookings.fragments.OngoingFragment
import javax.inject.Inject

class MyBookingsFragment : BaseFragment<FragmentMyBookingsBinding,MyBookingsViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_my_bookings
    override val viewModel: MyBookingsViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(MyBookingsViewModel::class.java)
    private lateinit var mBinding:FragmentMyBookingsBinding
    lateinit var mAdapter:ViewPagerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        init()
    }

    private fun init(){
        mAdapter = ViewPagerAdapter(childFragmentManager)
        mAdapter.add(OngoingFragment(),"ONGOING")
        mAdapter.add(HistoryFragment(),"HISTORY")
        mBinding.bookingsViewPager.adapter = mAdapter
        mBinding.bookingsTabLayout.setupWithViewPager(mBinding.bookingsViewPager)
   }

    class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun add(fragment: Fragment, title:String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

}
package com.inses.ui.bookings.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentHistoryBinding
import com.inses.ui.base.BaseFragment
import com.inses.ui.home.HomeActivity
import com.inses.utils.onClick
import javax.inject.Inject

class HistoryFragment : BaseFragment<FragmentHistoryBinding,HistoryViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_history
    override val viewModel: HistoryViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(HistoryViewModel::class.java)
    lateinit var mBinding: FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        init()
        setUp()
        subscribeToLiveData()
    }

    private fun init(){
        mBinding.book.onClick {
            mBinding.book.visibility = View.GONE
            mBinding.progressBar.visibility = View.VISIBLE
            startActivity(Intent(requireContext(), HomeActivity::class.java))
        }
    }

    private fun setUp(){

    }

    private fun subscribeToLiveData(){

    }

}
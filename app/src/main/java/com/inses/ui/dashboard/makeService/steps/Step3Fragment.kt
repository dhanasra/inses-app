package com.inses.ui.dashboard.makeService.steps

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentStep3Binding
import com.inses.timeItem
import com.inses.ui.base.BaseFragment
import com.inses.ui.dashboard.makeService.MakeServiceViewModel
import com.inses.utils.DateUtils.getToday
import com.inses.utils.DateUtils.getTomorrow
import com.inses.utils.MakeServiceUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderTitleTextTwo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class Step3Fragment : BaseFragment<FragmentStep3Binding, Step3ViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_step3
    override val viewModel: Step3ViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(Step3ViewModel::class.java)
    private lateinit var mBinding: FragmentStep3Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init()
    }

    private fun init(){
        mBinding.next.onClick {
            if(viewModel.serviceDate.get()!!.isEmpty()){
                showSnackBar("Invalid","Please Select service date","")
            }else if(viewModel.serviceTime.get()!!.isEmpty()){
                showSnackBar("Invalid","Please Select service time","")
            }else {
                mBinding.next.visibility = View.GONE
                mBinding.progressBar.visibility = View.VISIBLE
                RxBus.publish(MakeServiceUiEvent.Navigate(MakeServiceViewModel.Screen.BOOK,viewModel.serviceDate.get()!!,viewModel.serviceTime.get()))
            }
        }
        val fdate = mBinding.fDate.findViewById(R.id.date) as TextView
        fdate.text = getToday()[0]
        val fday = mBinding.fDate.findViewById(R.id.day) as TextView
        fday.text = getToday()[1]
        val sdate = mBinding.sDate.findViewById(R.id.date) as TextView
        sdate.text = getTomorrow()[0]
        val sDay = mBinding.sDate.findViewById(R.id.day) as TextView
        sDay.text = getTomorrow()[1]
        val pdate = mBinding.pDate.findViewById(R.id.date) as TextView
        pdate.visibility = View.GONE
        val pday = mBinding.pDate.findViewById(R.id.day) as TextView
        pday.text = "Pick Date"


        val fDateL = mBinding.fDate.findViewById(R.id.dateLayout) as LinearLayout
        val sDateL = mBinding.sDate.findViewById(R.id.dateLayout) as LinearLayout
        val pDateL = mBinding.pDate.findViewById(R.id.dateLayout) as LinearLayout

        fDateL.onClick { dateClickEvent(0) }
        sDateL.onClick { dateClickEvent(1) }
        pDateL.onClick { dateClickEvent(2) }
    }

    private fun setUp(){
        mBinding.scheduleEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("schedule")
                text("Schedule Your Service")
                txtSize("medium")
                txtColor("b")
                back(View.OnClickListener {
                    requireActivity().onBackPressed()
                })
            }
        }

        val timeList = listOf(
            "10AM to 11AM",
            "11AM to 12PM",
            "12PM to 1PM",
            "1PM to 2PM",
            "2PM to 3PM",
            "3PM to 4PM",
            "4PM to 5PM"
        )
        val list = arrayListOf(false, false, false, false, false, false, false)
        mBinding.timeEpoxyRecyclerView.withModels {
            timeList.forEachIndexed { index, s ->
                timeItem {
                    id(s)
                    text(s)
                    isSelected(list[index])
                    onClick(View.OnClickListener {
                        viewModel.serviceTime.set(s)
                        list.forEachIndexed { indx, b ->
                            list[indx] = index == indx
                            Log.d("value", list[index].toString())
                        }
                        mBinding.timeEpoxyRecyclerView.requestModelBuild()
                    })
                }
            }

        }
    }

    private fun dateClickEvent(i: Int){
        val fDateL = mBinding.fDate.findViewById(R.id.dateLayout) as LinearLayout
        val sDateL = mBinding.sDate.findViewById(R.id.dateLayout) as LinearLayout
        val pDateL = mBinding.pDate.findViewById(R.id.dateLayout) as LinearLayout

        fDateL.setBackgroundResource(R.drawable.grey_back)
        sDateL.setBackgroundResource(R.drawable.grey_back)
        pDateL.setBackgroundResource(R.drawable.grey_back)

        if(i==0){
            fDateL.setBackgroundResource(R.drawable.selected_back)
            viewModel.serviceDate.set(getToday()[2])
        }else if(i==1){
            sDateL.setBackgroundResource(R.drawable.selected_back)
            viewModel.serviceDate.set(getTomorrow()[2])
        }else if(i==2){
            pDateL.setBackgroundResource(R.drawable.selected_back)
            datePicker()
        }
    }

    private fun datePicker() {
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val dateTime = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                val df = SimpleDateFormat("dd-M-yyyy",Locale.ENGLISH)
                val dateObject = df.parse(dateTime)
                val day = SimpleDateFormat("EEE",Locale.ENGLISH).format(dateObject)
                val pdate = mBinding.pDate.findViewById(R.id.date) as TextView
                pdate.visibility = View.VISIBLE
                pdate.text = dayOfMonth.toString()
                viewModel.serviceDate.set(dateObject.time.toString())
                val pday = mBinding.pDate.findViewById(R.id.day) as TextView
                pday.text = day
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

}
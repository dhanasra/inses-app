package com.inses.ui.dashboard.makeService.steps

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.inses.BR
import com.inses.R
import com.inses.databinding.FragmentStep2Binding
import com.inses.ui.base.BaseFragment
import com.inses.ui.dashboard.makeService.MakeServiceViewModel
import com.inses.utils.MakeServiceUiEvent
import com.inses.utils.RxBus
import com.inses.utils.onClick
import com.inses.utils.withModels
import com.inses.viewHolderTitleTextTwo
import javax.inject.Inject


class Step2Fragment : BaseFragment<FragmentStep2Binding, Step2ViewModel>(),OnMapReadyCallback,AdapterView.OnItemClickListener {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_step2
    override val viewModel: Step2ViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(Step2ViewModel::class.java)
    private lateinit var mBinding: FragmentStep2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?){

        val stringArray = requireActivity().resources.getStringArray(R.array.places)

        val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item,stringArray)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mBinding.spinner.adapter = dataAdapter


        mBinding.next.onClick {
            viewModel.area.set(mBinding.spinner.selectedItem.toString())

            if(viewModel.locality.get()!!.trim().isEmpty()){
                showSnackBar("Error", "Locality should not be empty", "")
            }else if(viewModel.no.get()!!.trim().isEmpty()){
                showSnackBar("Error", "House no should not be empty", "")
            }else if(viewModel.street.get()!!.trim().isEmpty()){
                showSnackBar("Error", "Street Address should not be empty", "")
            }else if(viewModel.phoneNumber.get()!!.trim().isEmpty()){
                showSnackBar("Error", "Phone number should not be empty", "")
            }else if(viewModel.area.get()!!.trim().isEmpty()){
                showSnackBar("Error", "Select your area", "")
            }else if(viewModel.nearBy.get()!!.trim().isEmpty()){
                showSnackBar("Error", "Enter your nearBy marks", "")
            }else{
                mBinding.next.visibility = View.GONE
                mBinding.progressBar.visibility = View.VISIBLE
                RxBus.publish(
                    MakeServiceUiEvent.Navigate(
                        MakeServiceViewModel.Screen.SCHEDULE,
                        viewModel.locality.get()!!.trim(),
                        viewModel.no.get()!!.trim(),
                        viewModel.street.get()!!.trim(),
                        viewModel.phoneNumber.get()!!.trim(),
                        viewModel.area.get()!!.trim(),
                        viewModel.nearBy.get()!!.trim()
                    )
                )

                Log.d("selected spinner item",mBinding.spinner.selectedItem.toString())
            }
        }
        mBinding.mapView.onCreate(savedInstanceState)
        mBinding.mapView.getMapAsync(this)
    }

    private fun setUp(){
        mBinding.addressEpoxyRecyclerView.withModels {
            viewHolderTitleTextTwo {
                id("category")
                text("Select Your Address")
                txtSize("medium")
                txtColor("b")
                back(View.OnClickListener {
                    requireActivity().onBackPressed()
                })
            }
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
          mBinding.mapView.onResume()
            if (p0 != null) {
                p0.addMarker(
                    MarkerOptions()
                        .position(LatLng(9.908590, 78.154160)).title("INSES")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                        .draggable(false).visible(true)
                )
                p0.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(9.908590, 78.154160), 15F))
                p0.animateCamera(CameraUpdateFactory.zoomIn())
                p0.animateCamera(CameraUpdateFactory.zoomTo(15F), 2000, null)
            }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

}
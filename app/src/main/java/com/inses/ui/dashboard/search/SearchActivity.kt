package com.inses.ui.dashboard.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivitySearchBinding
import com.inses.ui.base.BaseActivity
import com.inses.ui.dashboard.makeService.MakeServiceActivity
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding,SearchViewModel>() {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_search
    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(SearchViewModel::class.java)
    lateinit var mBinding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        init()
    }

    private fun init(){
        mBinding.lv1.setOnItemClickListener { _, view, _, _ ->
            var i = Intent(this, MakeServiceActivity::class.java)
            val textview = view as TextView
                i.putExtra("service",textview.text.toString())
                startActivity(i)
        }

        var array = resources.getStringArray(R.array.services)
        val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,array)
        mBinding.lv1.adapter = adapter

        mBinding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(array.contains(query)){
                    adapter.filter.filter(query)
                }else{
                    Toast.makeText(this@SearchActivity, "No Match found", Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

}
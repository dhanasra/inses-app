package com.inses.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment(), BaseNavigator {

    var baseActivity: BaseActivity<*, *>? = null
        private set

//    val isNetworkConnected: Boolean
//        get() = baseActivity != null && baseActivity!!.isNetworkConnected()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val mActivity = context as BaseActivity<*, *>
            this.baseActivity = mActivity
            mActivity.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        // creating the fullscreen dialog
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun show(fragmentManager: androidx.fragment.app.FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    fun dismissDialog(tag: String) {
        dismiss()
        baseActivity!!.onFragmentDetached(tag)
    }


    override fun showSnackBar(title: String, msg: String, action: String?) {
        baseActivity?.showSnackBar(title, msg, action)
    }


    override fun hideSnackBar() {
        baseActivity?.hideSnackBar()
    }


    override fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }


}
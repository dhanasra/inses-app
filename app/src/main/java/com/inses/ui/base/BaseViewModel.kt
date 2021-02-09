package com.inses.ui.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel <N>():ViewModel(){


    private var mNavigator: WeakReference<N>? = null

    var navigator: N
        get() = mNavigator!!.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }
}
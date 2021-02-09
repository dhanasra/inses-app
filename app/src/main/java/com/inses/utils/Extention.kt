package com.inses.utils

import android.view.View

fun View.onClick(block: () -> Unit) {
    setOnClickListener { block() }
}
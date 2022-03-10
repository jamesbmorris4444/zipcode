package com.romtech.zipcode.main

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter

@BindingAdapter("layout_marginTop")
fun setMarginTop(v: View, topMargin: Int) {
    val layoutParams = v.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = topMargin
    v.layoutParams = layoutParams
    v.requestLayout()
}
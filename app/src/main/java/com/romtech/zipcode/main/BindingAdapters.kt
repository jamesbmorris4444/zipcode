package com.romtech.zipcode.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("layout_marginTop")
fun setMarginTop(v: View, topMargin: Int) {
    val layoutParams = v.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = topMargin
    v.layoutParams = layoutParams
    v.requestLayout()
}

@BindingAdapter("focused_upper_text_hint_color")
fun setUpperHintColor(textInputLayout: TextInputLayout, color: String) {
    try {
        val field = TextInputLayout::class.java.getDeclaredField("focusedTextColor")
        field.isAccessible = true
        val states = arrayOf(intArrayOf())
        val colors = intArrayOf(Color.parseColor(color))
        val myList = ColorStateList(states, colors)
        field.set(textInputLayout, myList)
        val method = textInputLayout::class.java.getDeclaredMethod("updateLabelState", Boolean::class.javaPrimitiveType, Boolean::class.javaPrimitiveType)
        method.isAccessible = true
        method.invoke(textInputLayout, true, true)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
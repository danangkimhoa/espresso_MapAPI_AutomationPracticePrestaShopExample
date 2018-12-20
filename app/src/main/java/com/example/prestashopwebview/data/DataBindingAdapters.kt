package com.example.prestashopwebview.data

import android.databinding.BindingAdapter
import android.widget.TextView

object DataBindingAdapters {

    @BindingAdapter("textResource")
    fun setTextResource(textView: TextView, resource: Int) {
        textView.setText(resource)
    }
}
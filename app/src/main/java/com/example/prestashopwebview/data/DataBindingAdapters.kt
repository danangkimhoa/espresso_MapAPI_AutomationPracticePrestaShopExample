package com.example.prestashopwebview.data

import android.widget.TextView
import androidx.databinding.BindingAdapter

object DataBindingAdapters {

    @BindingAdapter("textResource")
    fun setTextResource(textView: TextView, resource: Int) {
        textView.setText(resource)
    }
}
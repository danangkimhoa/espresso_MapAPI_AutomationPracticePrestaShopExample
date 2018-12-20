package com.example.prestashopwebview

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.inputmethod.InputMethodManager


object Utils {
    fun hideKeyboard(activity: FragmentActivity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
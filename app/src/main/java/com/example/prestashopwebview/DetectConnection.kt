package com.example.prestashopwebview

import android.content.Context
import android.net.ConnectivityManager

object DetectConnection {
    fun checkInternetConnection(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return (connectivityManager.activeNetworkInfo != null
                && connectivityManager.activeNetworkInfo.isConnected)
    }
}
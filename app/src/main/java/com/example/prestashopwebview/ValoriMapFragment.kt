package com.example.prestashopwebview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ValoriMapFragment: Fragment() {

    companion object {
        fun newInstance(): ValoriMapFragment {
            return ValoriMapFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.valori_map_fragment, container, false)
    }

}
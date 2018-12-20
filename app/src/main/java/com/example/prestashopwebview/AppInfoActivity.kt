package com.example.prestashopwebview

import android.content.Intent
import android.icu.text.IDNA
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import com.example.prestashopwebview.Utils.hideKeyboard
import com.example.prestashopwebview.data.AddressMap
import kotlinx.android.synthetic.main.activity_app_info.*
import kotlinx.android.synthetic.main.bottom_navigation_bar.*
import kotlinx.android.synthetic.main.valori_map_fragment.*

class AppInfoActivity : AppCompatActivity(), ValoriMapFragment.OnMapSelected {

    override fun onMapSelected() {
        var mapFragment = supportFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG) as? ValoriMapFragment
        if (mapFragment == null) {
            mapFragment = ValoriMapFragment.newInstance(AddressMap(getString(R.string.app_info_body_text)))
        }
        supportFragmentManager.beginTransaction().remove(mapFragment).commit();
        supportFragmentManager.executePendingTransactions();
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        hideUnusedNavigationButtons()
        setBackBehaviourToFinishActivity()

        if (savedInstanceState == null) {
            var infoOverviewFragment = supportFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG) as? InfoOverViewFragment
            if (infoOverviewFragment == null) {
                infoOverviewFragment = InfoOverViewFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, infoOverviewFragment, InfoOverViewFragment.FRAGMENT_TAG)
                    .commit()
        }
    }


    private fun setBackBehaviourToFinishActivity() {
        back_button.setOnClickListener {
            finish()
        }
    }

    private fun hideUnusedNavigationButtons() {
        forward_button.visibility = View.INVISIBLE
        leave_app_button.visibility = View.INVISIBLE
        info_button.visibility = View.INVISIBLE
    }
}

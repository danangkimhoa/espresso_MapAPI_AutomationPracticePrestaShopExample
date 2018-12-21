package com.example.prestashopwebview.appInfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.prestashopwebview.R
import com.example.prestashopwebview.Utils
import com.example.prestashopwebview.data.AddressMap
import kotlinx.android.synthetic.main.bottom_navigation_bar.*

class AppInfoActivity : AppCompatActivity(), ValoriMapFragment.OnMapSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)
        hideUnusedNavigationButtons()
        setBackBehaviourToFinishActivity()
        setUpMapFragment()
        setUpInfoFragment()
    }

    private fun setUpInfoFragment() {
        var infoOverviewFragment = supportFragmentManager.findFragmentByTag(InfoOverViewFragment.FRAGMENT_TAG)
            if (infoOverviewFragment == null) {
                infoOverviewFragment = InfoOverViewFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.text_fragment_container, infoOverviewFragment, InfoOverViewFragment.FRAGMENT_TAG)
                    .commit()
    }

    private fun setUpMapFragment() {
        var mapFragment = supportFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG)
            if (mapFragment == null) {
                mapFragment = ValoriMapFragment.newInstance(AddressMap(getString(R.string.app_info_body_text)))
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.map_fragment_container, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                    .commit()
    }

    override fun onMapSelected() {
        Utils.hideKeyboard(this)
        var mapFragment = supportFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG) as? ValoriMapFragment
        if (mapFragment == null) {
            mapFragment = ValoriMapFragment.newInstance(AddressMap(getString(R.string.app_info_body_text)))
        }
        var textFragment = supportFragmentManager.findFragmentByTag(InfoOverViewFragment.FRAGMENT_TAG) as? InfoOverViewFragment
        if (textFragment == null) {
            textFragment = InfoOverViewFragment.newInstance()
        }
        supportFragmentManager.beginTransaction()
                .remove(textFragment)
                .replace(R.id.map_fragment_container, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
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

package com.example.prestashopwebview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.bottom_navigation_bar.*
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_app_info.*


class AppInfoActivity : AppCompatActivity(), OnMapReadyCallback {
    val VALORI_LATITUDE_AND_LONGITUDE = LatLng(52.0673941, 5.0841556)
    val ZOOM_LEVEL = 11f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        hideUnusedNavigationButtons()
        setBackBehaviourToFinishActivity()

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        val mapFragment : SupportMapFragment? =
                supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
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

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just move the camera to Sydney and add a marker in Sydney.
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return
        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(VALORI_LATITUDE_AND_LONGITUDE, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(VALORI_LATITUDE_AND_LONGITUDE))
        }
    }
}

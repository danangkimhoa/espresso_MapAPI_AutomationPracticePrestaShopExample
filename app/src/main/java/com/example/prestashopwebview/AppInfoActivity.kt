package com.example.prestashopwebview

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.WindowManager
import com.example.prestashopwebview.Utils.hideKeyboard
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.bottom_navigation_bar.*
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_app_info.*
import org.jetbrains.anko.email


class AppInfoActivity : AppCompatActivity(), OnMapReadyCallback {
    val VALORI_LATITUDE_AND_LONGITUDE = LatLng(52.0673941, 5.0841556)
    val ZOOM_LEVEL = 11f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)


        hideUnusedNavigationButtons()
        setBackBehaviourToFinishActivity()
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        ConstraintSet().connect(bottom_navigation_bar.id, ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,0)
//        email_text_box.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                bottom_navigation_bar.visibility = View.GONE
//            } else {
//                bottom_navigation_bar.visibility = View.VISIBLE
//            }
//        }

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        val mapFragment : SupportMapFragment? =
                supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        submit_email.setOnClickListener {
            if (email_text_box.text.isBlank()) {
                email_input_layout.setError("Enter a message please"); // show error
            } else {
                hideKeyboard(this)
                submitEmail(email_text_box.text.toString())
            }
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

    private fun submitEmail(body: String) {
        val emailAdresses = arrayOf("test@test.nl")
        composeEmail(emailAdresses, "Test email subject", body)
    }

    private fun composeEmail(addresses: Array<String>, subject: String, body: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}

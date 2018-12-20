package com.example.prestashopwebview

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prestashopwebview.data.AddressMap
import com.example.prestashopwebview.databinding.ValoriMapFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_app_info.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

private const val LOCATION_PERMISSION_REQUEST_CODE = 1

class ValoriMapFragment: Fragment(), OnMapReadyCallback, EasyPermissions.PermissionCallbacks {
    companion object {
        fun newInstance(): ValoriMapFragment {
            return ValoriMapFragment()
        }
        val FRAGMENT_TAG = "FRAGMENT_TAG:${ValoriMapFragment::class.java.simpleName}"
    }

    fun setData(address: AddressMap) {
        addressObject = address
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMapSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnMapSelected.")
        }
    }

    private lateinit var listener: OnMapSelected
    val VALORI_LATITUDE_AND_LONGITUDE = LatLng(52.0673941, 5.0841556)
    val ZOOM_LEVEL = 11f
    lateinit var addressObject: AddressMap

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * [.onRequestPermissionsResult].
     */
    private var showPermissionDeniedDialog = false
    private lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
            val mapBinding: ValoriMapFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.valori_map_fragment, container, false)
            mapBinding.addressMap = addressObject
        return mapBinding.root//inflater.inflate(R.layout.valori_map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMap()
    }

    override fun onResume() {
        super.onResume()
        setUpMap()
        if (showPermissionDeniedDialog) {
            AlertDialog.Builder(context).apply {
                setPositiveButton("Ok", null)
                setMessage("Permission Denied")
                create()
            }.show()
            showPermissionDeniedDialog = false
        }
    }

    private fun setUpMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just move the camera to Sydney and add a marker in Sydney.
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return

        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(VALORI_LATITUDE_AND_LONGITUDE, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(VALORI_LATITUDE_AND_LONGITUDE))
        }
        map.setOnMapClickListener{ listener.onMapSelected() }
        map.uiSettings.isScrollGesturesEnabled = false
        map.uiSettings.isZoomGesturesEnabled = true
        enableMyLocation()

    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(LOCATION_PERMISSION_REQUEST_CODE)
    private fun enableMyLocation() {
        // Enable the location layer. Request the location permission if needed.
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        if (EasyPermissions.hasPermissions(context!!, *permissions)) {
            map.isMyLocationEnabled = true
        } else {
            // if permissions are not currently granted, request permissions
            EasyPermissions.requestPermissions(this,
                    "permission rationale",
                    LOCATION_PERMISSION_REQUEST_CODE, *permissions)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,
                permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }


    interface OnMapSelected {
        fun onMapSelected()
    }
}



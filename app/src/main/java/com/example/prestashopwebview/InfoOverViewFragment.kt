package com.example.prestashopwebview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prestashopwebview.data.AddressMap
import kotlinx.android.synthetic.main.info_overview_fragment.*

class InfoOverViewFragment: Fragment() {

    companion object {
        fun newInstance(): InfoOverViewFragment {
            return InfoOverViewFragment()
        }
        val FRAGMENT_TAG = "FRAGMENT_TAG:${InfoOverViewFragment::class.java.simpleName}"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState == null) {
            var mapFragment = childFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG) as? ValoriMapFragment
            if (mapFragment == null) {
                mapFragment = ValoriMapFragment.newInstance()
            }
            childFragmentManager.beginTransaction()
                    .replace(R.id.map_fragment, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                    .commit()
            mapFragment.setData(AddressMap(getString(R.string.app_info_body_text)))
        }
        return inflater.inflate(R.layout.info_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit_email.setOnClickListener {
            if (email_text_box.text != null) {
                if (email_text_box.text!!.isBlank()) {
                    email_input_layout.setError("Enter a message please"); // show error
                } else {
                    Utils.hideKeyboard(activity!!)
                    submitEmail(email_text_box.text.toString())
                }
            }
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
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        }
    }
}
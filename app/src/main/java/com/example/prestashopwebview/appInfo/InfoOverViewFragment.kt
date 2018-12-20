package com.example.prestashopwebview.appInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prestashopwebview.R
import com.example.prestashopwebview.Utils
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
        setUpMapFragment(savedInstanceState)
        return inflater.inflate(R.layout.info_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEmailButton()
    }

    private fun setUpMapFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            var mapFragment = childFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG) as? ValoriMapFragment
            if (mapFragment == null) {
                mapFragment = ValoriMapFragment.newInstance(AddressMap(getString(R.string.app_info_body_text)))
            }
            childFragmentManager.beginTransaction()
                    .replace(R.id.map_fragment, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                    .commit()
        }
    }

    private fun setUpEmailButton() {
        submit_email.setOnClickListener {
            if (email_text_box.text != null) {
                if (email_text_box.text!!.isBlank()) {
                    email_input_layout.error = "Enter a message please"
                } else {
                    Utils.hideKeyboard(activity!!)
                    submitEmail(email_text_box.text.toString())
                    email_input_layout.error = null
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
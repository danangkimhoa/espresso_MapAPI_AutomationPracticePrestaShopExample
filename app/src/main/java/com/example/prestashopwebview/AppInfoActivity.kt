package com.example.prestashopwebview

import android.content.Intent
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
            mapFragment = ValoriMapFragment.newInstance()
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.root_layout, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        if (savedInstanceState == null) {
            var mapFragment = supportFragmentManager.findFragmentByTag(ValoriMapFragment.FRAGMENT_TAG) as? ValoriMapFragment
            if (mapFragment == null) {
                mapFragment = ValoriMapFragment.newInstance()
            }
                supportFragmentManager.beginTransaction()
                        .replace(R.id.map_fragment, mapFragment, ValoriMapFragment.FRAGMENT_TAG)
                        .commit()
                mapFragment.setData(AddressMap(getString(R.string.app_info_body_text)))
        }

        hideUnusedNavigationButtons()
        setBackBehaviourToFinishActivity()

        submit_email.setOnClickListener {
            if (email_text_box.text != null) {
                if (email_text_box.text!!.isBlank()) {
                    email_input_layout.setError("Enter a message please"); // show error
                } else {
                    hideKeyboard(this)
                    submitEmail(email_text_box.text.toString())
                }
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

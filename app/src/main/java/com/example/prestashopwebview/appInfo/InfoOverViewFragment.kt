package com.example.prestashopwebview.appInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prestashopwebview.R
import com.example.prestashopwebview.Utils
import kotlinx.android.synthetic.main.info_overview_fragment.*

class InfoOverViewFragment: Fragment() {
    companion object {
        fun newInstance(): InfoOverViewFragment {
            return InfoOverViewFragment()
        }
        val FRAGMENT_TAG = "FRAGMENT_TAG:${InfoOverViewFragment::class.java.simpleName}"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEmailButton()
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
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        }
    }
}
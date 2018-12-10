package com.example.prestashopwebview

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation_bar.*
import android.webkit.WebView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setWebViewConfig()
        restoreStateOfWebviewOrOpenDefaultUri(savedInstanceState)
        setBackButtonToNavigateBackInWebViewHistory()
        setForwardButtonToNavigateForwardInWebViewHistory()
        setLeaveAppButtonToOpenUrlInBrowser()
        setInfoButtonToOpenAppInfoActivity()
    }

    private fun setInfoButtonToOpenAppInfoActivity() {
        info_button.setOnClickListener {
            val intent = Intent(this, AppInfoActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun setWebViewConfig() {
        prestashop_webview.settings.javaScriptEnabled = true
        prestashop_webview.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                back_button.isEnabled = prestashop_webview.canGoBack()
                forward_button.isEnabled = prestashop_webview.canGoForward()
                super.onPageFinished(view, url)
             }
        }
    }

    private fun setLeaveAppButtonToOpenUrlInBrowser() {
        leave_app_button.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(prestashop_webview.url
                        ?: getString(R.string.prestashop_url)))
                this.startActivity(intent)
            } catch (e: Throwable) {
                Toast.makeText(this, "Het openen van de link is niet gelukt", Toast.LENGTH_LONG)
                        .apply {
                            show()
                        }
            }
        }
    }

    private fun setForwardButtonToNavigateForwardInWebViewHistory() {
        forward_button.isEnabled = prestashop_webview.canGoForward()
        forward_button.setOnClickListener {
            if (prestashop_webview.canGoForward()) {
                prestashop_webview.goForward()
                forward_button.isEnabled = prestashop_webview.canGoForward()
            } else {
                Toast.makeText(this, getString(R.string.navigate_forward_error), Toast.LENGTH_SHORT).apply { show() }
            }
        }
    }

    private fun setBackButtonToNavigateBackInWebViewHistory() {
        back_button.isEnabled = prestashop_webview.canGoBack()
        back_button.setOnClickListener {
            if (prestashop_webview.canGoBack()) {
                prestashop_webview.goBack()
                back_button.isEnabled = prestashop_webview.canGoBack()
            } else {
                Toast.makeText(this, getString(R.string.navigate_back_error), Toast.LENGTH_SHORT).apply { show() }
            }
        }
    }

    private fun restoreStateOfWebviewOrOpenDefaultUri(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            prestashop_webview.restoreState(savedInstanceState);
        } else {
            prestashop_webview.loadUrl(getString(R.string.prestashop_url))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        prestashop_webview.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && prestashop_webview.canGoBack()) {
            prestashop_webview.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}

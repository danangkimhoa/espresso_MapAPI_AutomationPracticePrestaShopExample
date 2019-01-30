package com.example.prestashopwebview.pageobjects.webviewpages

import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.Locator

class PrestaShopHomePage {

    var loginButton =  onWebView().withElement(findElement(Locator.CLASS_NAME, "login"))

    fun goToLoginPage(): LoginPage {
        loginButton.perform(webClick())
        return LoginPage()
    }
}
package com.example.prestashopwebview.base

import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.Locator

abstract class BaseWebPage {

    var pageTitle = onWebView().withElement(findElement(Locator.TAG_NAME, "title"))
}
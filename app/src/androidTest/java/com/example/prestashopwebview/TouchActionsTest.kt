package com.example.prestashopwebview

import android.util.Log
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.model.Atoms
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.pageobjects.MainActivityPage
import org.hamcrest.CoreMatchers
import org.junit.Test

class TouchActionsTest: BaseTest() {

    @Test
    fun pullToRefresh() {
        Web.onWebView()
                .withElement(DriverAtoms.findElement(Locator.CLASS_NAME, "login"))
                .perform(DriverAtoms.webClick())
                .withElement(DriverAtoms.findElement(Locator.TAG_NAME, "title"))
                .check(WebViewAssertions.webMatches(Atoms.getTitle(), CoreMatchers.`is`(CoreMatchers.equalTo("Login - My Store"))))

        MainActivityPage().pullToRefresh()

        Web.onWebView()
                .withElement(DriverAtoms.findElement(Locator.TAG_NAME, "title"))
                .check(WebViewAssertions.webMatches(Atoms.getTitle(), CoreMatchers.`is`(CoreMatchers.equalTo("My Store"))))
    }

    @Test
    fun selectGoogleMapWithSingleTap() {
        MainActivityPage().goToAppInfoPage().selectGoogleMapWithSingleTap()
    }

    @Test
    fun selectGoogleMapWithElement() {
        MainActivityPage().goToAppInfoPage().selectGoogleMapWithElement()

    }

    @Test
    fun selectGoogleMapWithLocationPoint() {
        MainActivityPage().goToAppInfoPage().selectGoogleMapWithLocationPoint()
    }

    @Test
    fun selectGoogleMapWithSimpleClick() {
        MainActivityPage().goToAppInfoPage().clickGoogleMap()
    }
}
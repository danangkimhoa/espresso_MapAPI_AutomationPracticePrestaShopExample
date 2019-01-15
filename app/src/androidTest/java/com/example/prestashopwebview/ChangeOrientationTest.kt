package com.example.prestashopwebview

import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.model.Atoms.getTitle
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.runner.AndroidJUnit4
import com.example.prestashopwebview.base.BaseTest
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChangeOrientationTest: BaseTest() {

    @Test
    fun changingOrientationShouldPreserveWebViewState() {
        onWebView()
                .withElement(findElement(Locator.CLASS_NAME, "login")) // similar to onView(withId(...))
                .perform(webClick()) // Similar to perform(click())

                .withElement(findElement(Locator.TAG_NAME, "title"))
                // Similar to check(matches(...))
                .check(webMatches(getTitle(), `is`(equalTo("Login - My Store"))))

        
    }
}
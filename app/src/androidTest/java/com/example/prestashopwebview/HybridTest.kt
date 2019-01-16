package com.example.prestashopwebview

import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.model.Atoms
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import com.example.prestashopwebview.base.BaseTest
import org.hamcrest.CoreMatchers
import org.junit.Test

class HybridTest: BaseTest() {

    @Test
    fun shouldBeAbleToReuseCodeFromWebAppTest(){
        Web.onWebView()
                .withElement(DriverAtoms.findElement(Locator.CLASS_NAME, "login"))
                .perform(DriverAtoms.webClick())
                .withElement(DriverAtoms.findElement(Locator.ID, "email_create"))
                .perform(DriverAtoms.webKeys("Garbage"))
                .withElement(findElement(Locator.NAME, "SubmitCreate"))
                .perform(webClick())
                .withElement(findElement(Locator.CSS_SELECTOR, "#center_column > div.alert.alert-danger > ol > li"))
                .check(WebViewAssertions.webMatches(getText(), CoreMatchers.`is`(CoreMatchers.equalTo("Invalid email address."))))
    }

}
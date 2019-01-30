package com.example.prestashopwebview

import android.os.Build
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.base.TestUtils
import com.example.prestashopwebview.pageobjects.AppInfoPage
import com.example.prestashopwebview.pageobjects.MainActivityPage
import org.junit.Before
import org.junit.Test

class GPSTest: BaseTest() {

    @Test
    fun GPSTest() {
        TestUtils.mockLocation(52.070499, 4.300700)

        MainActivityPage()
                .goToAppInfoPage().clickMyLocationButton()

        TestUtils.mockLocation(49.0, 123.0)

        AppInfoPage().clickMyLocationButton()
    }
}

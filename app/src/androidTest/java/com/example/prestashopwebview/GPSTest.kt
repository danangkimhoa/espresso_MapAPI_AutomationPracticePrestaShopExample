package com.example.prestashopwebview

import android.os.Build
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.base.TestUtils
import org.junit.Before
import org.junit.Test

class GPSTest: BaseTest() {

    @Before
    fun grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            with(getInstrumentation().uiAutomation) {
                executeShellCommand("appops set " + InstrumentationRegistry.getInstrumentation().targetContext.packageName + " android:mock_location allow")
                Thread.sleep(1000)
            }
        }
    }

    @Test
    fun GPSTest() {
        TestUtils.mockLocation(52.070499, 4.300700)
        Espresso.onView(ViewMatchers.withId(R.id.info_button)).perform(ViewActions.click())
        onView(withContentDescription("My Location"))
        TestUtils.mockLocation(49.0, 123.0)
        onView(withContentDescription("My Location")).perform(click())
    }

}
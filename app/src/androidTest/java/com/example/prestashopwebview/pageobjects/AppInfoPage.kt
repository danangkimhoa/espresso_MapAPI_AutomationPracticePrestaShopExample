package com.example.prestashopwebview.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.prestashopwebview.R
import com.example.prestashopwebview.base.TestUtils

class AppInfoPage {

    var editTextBox = onView(withId(R.id.email_text_box))
    private var myLocationButton = onView(withContentDescription("My Location"))
    private var googleMap = onView(withId(R.id.map_fragment_container))

    fun enterTextIntoTextBox(text: String): AppInfoPage {
        editTextBox.perform(scrollTo(), replaceText(text))
        return this
    }

    fun clickMyLocationButton(): AppInfoPage {
        myLocationButton.perform(click())
        return this
    }

    fun clickGoogleMap(): AppInfoPage {
        googleMap.perform(click())
        TestUtils.waitUntilViewInteractionIsNotDisplayed(withId(R.id.email_text_box))
        //editTextBox.check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        return this
    }
}
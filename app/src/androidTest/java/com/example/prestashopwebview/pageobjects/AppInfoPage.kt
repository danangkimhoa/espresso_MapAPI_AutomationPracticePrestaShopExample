package com.example.prestashopwebview.pageobjects

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.prestashopwebview.R
import com.example.prestashopwebview.base.TestUtils
import com.example.prestashopwebview.base.viewactions.CustomViewActions

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
        waitUntilEditTextViewIsNotDisplayed()
        //editTextBox.check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        return this
    }

    fun selectGoogleMapWithSingleTap() {
        var singleTap = GeneralClickAction(Tap.SINGLE,
                GeneralLocation.VISIBLE_CENTER,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY)

        googleMap.perform(singleTap)
        waitUntilEditTextViewIsNotDisplayed()
    }

    fun selectGoogleMapWithElement() {
        var singleTap = GeneralClickAction(Tap.SINGLE,
                GeneralLocation.VISIBLE_CENTER,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY)

        googleMap.perform(singleTap)
        waitUntilEditTextViewIsNotDisplayed()
    }

    fun selectGoogleMapWithLocationPoint() {
        googleMap.perform(CustomViewActions().clickViewCoordinates())
        waitUntilEditTextViewIsNotDisplayed()
    }

    private fun waitUntilEditTextViewIsNotDisplayed() {
        TestUtils.waitUntilViewInteractionIsNotDisplayed(withId(R.id.email_text_box))
    }
}
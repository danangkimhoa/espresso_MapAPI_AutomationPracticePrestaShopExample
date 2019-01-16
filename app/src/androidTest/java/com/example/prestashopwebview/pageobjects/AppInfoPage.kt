package com.example.prestashopwebview.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.prestashopwebview.R

class AppInfoPage {

    var editTextBox = onView(withId(R.id.email_text_box))
    private var myLocationButton = onView(withContentDescription("My Location"))

    fun enterTextIntoTextBox(text: String): AppInfoPage {
        editTextBox.perform(scrollTo(), replaceText(text))
        return this
    }

    fun clickMyLocationButton(): AppInfoPage {
        myLocationButton.perform(click())
        return this
    }
}
package com.example.prestashopwebview.pageobjects

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import com.example.prestashopwebview.R

class MainActivityPage {

    private var infoButton = Espresso.onView(ViewMatchers.withId(R.id.info_button))

    fun goToAppInfoPage(): AppInfoPage {
        infoButton.perform(click())
        return AppInfoPage()
    }
}
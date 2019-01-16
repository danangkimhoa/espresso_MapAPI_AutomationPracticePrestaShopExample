package com.example.prestashopwebview

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.base.TestUtils
import com.example.prestashopwebview.pageobjects.MainActivityPage
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

class HardwareButtonTest: BaseTest() {

    @Test
    fun navigateBackUsingHardwareButton() {
        MainActivityPage()
                .goToAppInfoPage()
                .clickGoogleMap()


        Espresso.pressBack()
        Espresso.pressBack()
        assertThat(TestUtils.getCurrentActivity()?.localClassName, equalTo("MainActivity"))
    }
}
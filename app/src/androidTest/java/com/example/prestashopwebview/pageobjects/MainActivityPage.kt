package com.example.prestashopwebview.pageobjects

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.*
import androidx.test.espresso.action.Swipe.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.prestashopwebview.R
import org.junit.Test

class MainActivityPage {

    private var infoButton = Espresso.onView(ViewMatchers.withId(R.id.info_button))
    private var swipeRefreshLayout = onView(withId(R.id.swipe))

    fun goToAppInfoPage(): AppInfoPage {
        infoButton.perform(click())
        return AppInfoPage()
    }

    fun pullToRefresh() {
        swipeRefreshLayout.perform(swipeDown(), swipeDown())
    }
}
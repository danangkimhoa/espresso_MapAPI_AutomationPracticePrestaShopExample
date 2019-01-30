package com.example.prestashopwebview.pageobjects

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.prestashopwebview.R
import com.example.prestashopwebview.pageobjects.webviewpages.PrestaShopHomePage

class MainActivityPage {

    private var infoButton = Espresso.onView(ViewMatchers.withId(R.id.info_button))
    private var swipeRefreshLayout = onView(withId(R.id.swipe))

    var prestaShopHomePage = PrestaShopHomePage()

    fun goToAppInfoPage(): AppInfoPage {
        infoButton.perform(click())
        return AppInfoPage()
    }

    fun pullToRefresh() {
        swipeRefreshLayout.perform(swipeDown(), swipeDown())
    }
}
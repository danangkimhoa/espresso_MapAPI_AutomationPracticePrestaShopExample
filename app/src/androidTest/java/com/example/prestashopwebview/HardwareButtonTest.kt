package com.example.prestashopwebview

import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.pageobjects.MainActivityPage
import org.junit.Test

class HardwareButtonTest: BaseTest() {

    @Test
    fun navigateBackUsingHardwareButton() {
        MainActivityPage().goToAppInfoPage()


    }
}
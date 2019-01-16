package com.example.prestashopwebview

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.base.TestUtils
import com.example.prestashopwebview.pageobjects.MainActivityPage
import org.junit.Test

class ClipBoardTest: BaseTest() {

    @Test
    fun getTextFromClipboard() {
        val testText = "Dit is test Text"
        TestUtils.setClipboardText(testText)
        MainActivityPage()
                .goToAppInfoPage()
                .enterTextIntoTextBox(TestUtils.getClipboardText())
                .editTextBox.check(matches(withText(testText)))
    }
}
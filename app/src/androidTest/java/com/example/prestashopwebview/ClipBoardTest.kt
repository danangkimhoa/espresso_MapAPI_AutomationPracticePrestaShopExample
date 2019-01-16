package com.example.prestashopwebview

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.prestashopwebview.base.BaseTest
import com.example.prestashopwebview.base.TestUtils
import org.junit.Test

class ClipBoardTest: BaseTest() {

    @Test
    fun getTextFromClipboard() {
        val testText = "Dit is test Text"
        TestUtils.setClipboardText(testText)
        onView(withId(R.id.info_button)).perform(click())
        onView(withId(R.id.email_text_box)).perform(scrollTo(), replaceText(TestUtils.getClipboardText()))
        onView(withId(R.id.email_text_box)).check(matches(withText(testText)))
    }
}
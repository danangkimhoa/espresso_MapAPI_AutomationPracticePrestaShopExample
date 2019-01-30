package com.example.prestashopwebview.base

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.example.prestashopwebview.MainActivity
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

abstract class BaseTest {

    /* Instantiate an IntentsTestRule object for MainActivity. */
    @Rule
    @JvmField
    var intentsRule: IntentsTestRule<MainActivity> = object : IntentsTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            TestUtils.clearSharedPrefs(InstrumentationRegistry.getInstrumentation().targetContext)
            super.beforeActivityLaunched()
        }
    }

    @get:Rule
    var watcher: TestRule = object : TestWatcher() {
        override fun failed(e: Throwable?, description: Description?) {
            // Take a screenshot on test failure for use in Spoon test report
             }
    }

    @Rule @JvmField
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

}
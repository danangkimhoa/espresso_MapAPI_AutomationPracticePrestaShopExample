package com.example.prestashopwebview.base


import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import android.content.Context.CLIPBOARD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.jetbrains.anko.clipboardManager
import android.os.SystemClock
import android.os.Build
import android.hardware.SensorManager.getAltitude
import android.location.LocationManager
import android.location.Criteria
import android.location.Location
import android.support.v4.content.ContextCompat.getSystemService




object TestUtils {
    private val KEY_SP_PACKAGE = "com.example.android.prestashopwebview_preferences"
    private val MAX_FIND_VIEWINTERACTION_TRIES = 100
    private val TIME_TO_WAIT_BETWEEN_TRIES = 50

    /**
     * Clears everything in the SharedPreferences
     */
    fun clearSharedPrefs(context: Context) {
        val prefs = context.getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }

    fun waitForLoadingOfView(viewMatcher: Matcher<View>): Boolean {
        var findViewInteractionCounter = 0
        while (findViewInteractionCounter < MAX_FIND_VIEWINTERACTION_TRIES) {
            if (viewExists(viewMatcher)) {
                return true
            } else {
                findViewInteractionCounter += 1
                wait(TIME_TO_WAIT_BETWEEN_TRIES)
            }
        }
        throw NoMatchingViewAfterWaitException("View not found after waiting ${viewMatcher}")
    }


    fun viewExists(viewMatcher: Matcher<View>): Boolean {
        try {
            onView(allOf(viewMatcher, isDisplayed())).check(matches(isDisplayed()))
            return true
        } catch (e: NoMatchingViewException) {
            try {
                // View was not completely displayed, so try to scroll to it.
                onView(viewMatcher).perform(scrollTo())
            } catch (e: Exception) {
                // Ignore this one.
            }
            return false
        }
    }

    fun wait(milliseconds: Int) {
        try {
            Thread.sleep(milliseconds.toLong())
        } catch (e: InterruptedException) {

        }
    }

    fun getClipboardText(): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var clipboardText = ""
        val clipboard =  context.clipboardManager
        if (clipboard.hasPrimaryClip()) clipboardText = clipboard.primaryClip.getItemAt(0).coerceToText(context).toString()
        return clipboardText
    }

    fun setClipboardText(text: String) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val clipboard = InstrumentationRegistry.getInstrumentation().targetContext.clipboardManager
            val clip: ClipData = ClipData.newPlainText("simple text", text)
            clipboard.primaryClip = clip
        }
    }

    private fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val resumedActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            for (act in resumedActivity) {
                currentActivity = act
                break
            }
        }
        return currentActivity
    }

    fun mockLocation(lat: Double, long: Double) {
        val lm = InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE

        val mocLocationProvider = LocationManager.GPS_PROVIDER//lm.getBestProvider( criteria, true );

        lm.addTestProvider(mocLocationProvider, false, false,
                false, false, true, true, true, 0, 5)
        lm.setTestProviderEnabled(mocLocationProvider, true)

        val loc = Location(mocLocationProvider)
        val mockLocation = Location(mocLocationProvider) // a string
        mockLocation.setLatitude(lat)  // double
        mockLocation.setLongitude(long)
        mockLocation.setAltitude(loc.getAltitude())
        mockLocation.setTime(System.currentTimeMillis())
        mockLocation.setAccuracy(1F)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mockLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos())
        }
        lm.setTestProviderLocation(mocLocationProvider, mockLocation)
    }

}

class NoMatchingViewAfterWaitException(message: String) : Exception(message)
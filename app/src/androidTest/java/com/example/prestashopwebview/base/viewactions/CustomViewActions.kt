package com.example.prestashopwebview.base.viewactions

import android.content.ContentValues.TAG
import android.util.Log
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.*
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class CustomViewActions {

    fun scrollToBottomOfScrollView(): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Scroll to the end of the scrollview using .fullScroll()"
            }

            override fun getConstraints(): Matcher<View> {
                return Matchers.instanceOf(ScrollView::class.java)
            }

            override fun perform(uiController: UiController?, view: View?) {
                val scrollView: ScrollView
                when (view) {
                    is ScrollView -> scrollView = view
                    else -> throw  Exception("scrolling to bottom can only be performed on a ScrollView")
                }
                try {
                    scrollView.fullScroll(View.FOCUS_DOWN)
                } catch (e: Exception) {
                    throw PerformException.Builder()
                            .withActionDescription(this.description)
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(e)
                            .build()
                }
                uiController?.loopMainThreadUntilIdle()
            }
        }
    }

    fun clickViewCoordinates(): ViewAction {
        return actionWithAssertions(object : ViewAction {
            override fun getDescription(): String {
                return "click on a view using coordinates"
            }

            override fun getConstraints(): Matcher<View> {
                return Matchers.instanceOf(View::class.java)
            }

            override fun perform(uiController: UiController?, view: View?) {
                var width = 0
                var height = 0
                val location = IntArray(2)

                if (view != null) {
                    width = view.width
                    height = view.height
                    view.getLocationOnScreen(location)
                }
                val x = location[0].toFloat()
                val y = location[1].toFloat()
                Log.d("Espresso", "X location ${x}")
                Log.d("Espresso", "Y location ${y}")
                Log.d("Espresso", "Width ${width}")
                Log.d("Espresso", "Height ${height}")

                val coordinates = FloatArray(2)
                coordinates.set(0, x+(width/2))
                coordinates.set(1, y+(height/2))

                val tapper = Tap.SINGLE
                val precision = Press.FINGER.describePrecision()
                val inputDevice = InputDevice.SOURCE_UNKNOWN
                val buttonState = 0

                var status: Tapper.Status = Tapper.Status.FAILURE
                var loopCount = 0

                while (status != Tapper.Status.SUCCESS && loopCount < 3) {
                    try {
                        status = tapper.sendTap(uiController, coordinates, precision, inputDevice, buttonState)
                        if (Log.isLoggable(TAG, Log.DEBUG)) {
                            Log.d(TAG, "perform: " + String.format(
                                    "%s - At Coordinates: %d, %d and precision: %d, %d", this.description, coordinates[0].toInt(),
                                    coordinates[1].toInt(), precision[0].toInt(), precision[1].toInt()))
                        }
                    } catch (re: RuntimeException) {
                        throw PerformException.Builder().withActionDescription(String
                                .format("%s - At Coordinates: %d, %d and precision: %d, %d",
                                        this.description, coordinates[0].toInt(), coordinates[1].toInt(),
                                        precision[0].toInt(), precision[1].toInt()))
                                .withViewDescription(HumanReadables.describe(view))
                                .withCause(re).build()
                    }
                }
            }
        })
    }
}

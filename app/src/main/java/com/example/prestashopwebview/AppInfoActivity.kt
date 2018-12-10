package com.example.prestashopwebview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.bottom_navigation_bar.*

class AppInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        hideUnusedNavigationButtons()
        setBackBehaviourToFinishActivity()
    }

    private fun setBackBehaviourToFinishActivity() {
        back_button.setOnClickListener {
            finish()
        }
    }

    private fun hideUnusedNavigationButtons() {
        forward_button.visibility = View.INVISIBLE
        leave_app_button.visibility = View.INVISIBLE
        info_button.visibility = View.INVISIBLE
    }
}

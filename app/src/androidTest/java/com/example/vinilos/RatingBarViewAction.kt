package com.example.vinilos

import android.view.View
import android.widget.RatingBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


class RatingBarViewAction(private val value: Float) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isAssignableFrom(RatingBar::class.java)
    }

    override fun getDescription(): String {
        return "It is a view action for rating bar"
    }

    override fun perform(uiController: UiController?, view: View?) {
        val ratingBar = view as RatingBar
        ratingBar.rating = value
    }

}
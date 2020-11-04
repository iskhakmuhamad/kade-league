package com.example.myleaguemvp.presenter.match

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.myleaguemvp.R
import com.example.myleaguemvp.view.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchMatchTest {
    @Rule
    @JvmField
    var activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearch() {
        onView(withId(R.id.rv_league)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.menu_match)).perform(click())
        onView(withId(R.id.rv_past_match)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.action_to_search)).perform(click())
        onView(withId(R.id.pb_search_match)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.pb_search_match)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search_menu)).perform(click())
        onView(withId(R.id.action_search_menu)).perform(typeText("argentina"))
        Thread.sleep(1500)
        onView(withId(R.id.rv_search_match)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        Thread.sleep(2000)
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )
        Thread.sleep(7000)
        onView(withId(R.id.tvd_league_event)).check(matches(isDisplayed()))
    }

}
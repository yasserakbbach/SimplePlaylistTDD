package com.yasserakbbach.simpleplaylisttdd

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import java.util.EnumSet.allOf

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists() {
        assertRecyclerViewItemCount(R.id.recyclerViewPlaylists, 10)
        onView(
            allOf(
                withId(R.id.textPlaylistName),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.recyclerViewPlaylists),
                        0,
                    )
                )
            )
        ).check(matches(withText("Hard Rock Cafe")))
         .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.textPlaylistCategory),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.recyclerViewPlaylists),
                        0,
                    )
                )
            )
        ).check(matches(withText("rock")))
         .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.imagePlaylist),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.recyclerViewPlaylists),
                        0,
                    )
                )
            )
        ).check(matches(withDrawable(R.drawable.playlist)))
         .check(matches(isDisplayed()))
    }

    private fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> =
        object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
}
package com.example.vinilos;

import androidx.test.espresso.DataInteraction;
import androidx.test.filters.LargeTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import com.example.vinilos.ui.MainActivity;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AssociateAlbumToBandTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void listAlbum() throws InterruptedException {
        ViewInteraction bandsButton = onView(allOf(withId(R.id.bandListButton)));
        bandsButton.perform(click());
        Thread.sleep(10000);

        ViewInteraction bandDetail = onView(withText("Queen"));
        bandDetail.perform(click());
        Thread.sleep(10000);

        ViewInteraction associateButton = onView(allOf(withId(R.id.associateAlbumButton)));
        associateButton.perform(click());
        Thread.sleep(10000);

        ViewInteraction albumItem = onView(withText("Poeta del pueblo"));
        albumItem.perform(scrollTo(), click());
        ViewInteraction associateAction = onView((withId(R.id.album_form_button)));
        associateAction.perform(scrollTo(), click());
        Thread.sleep(10000);
        onView((withText("Queen"))).check(matches(isDisplayed()));

    }
}

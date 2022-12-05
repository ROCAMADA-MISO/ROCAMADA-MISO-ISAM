package com.example.vinilos;

import static androidx.databinding.adapters.RatingBarBindingAdapter.setRating;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.vinilos.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateCommentAlbumTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void successfulCreateAlbum() throws InterruptedException {

        ViewInteraction AlbumButton = onView(allOf(withId(R.id.albumsListButton)));
        AlbumButton.perform(click());
        Thread.sleep(10000);

        ViewInteraction albumDetail = onView(withText("Poeta del pueblo"));
        albumDetail.perform(click());
        Thread.sleep(10000);
        onView((withText("Poeta del pueblo"))).check(matches(isDisplayed()));

        ViewInteraction createAlbumButton = onView(allOf(withId(R.id.album_addcomment_form_button)));
        createAlbumButton.perform(click());

        onView(withId(R.id.album_comment_rating_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.album_comment_rating_bar)).perform(new RatingBarViewAction(4.0F));
        Thread.sleep(10000);

        ViewInteraction commentField = onView(allOf(withId(R.id.albumCommentEditText)));
        commentField.perform(typeText("Comentario de prueba"));
        Thread.sleep(10000);

        ViewInteraction confirmCreateAlbumButton = onView(allOf(withId(R.id.album_comment_form_button)));
        confirmCreateAlbumButton.perform(scrollTo(), click());

        Espresso.pressBack();

        Thread.sleep(10000);
    }
}

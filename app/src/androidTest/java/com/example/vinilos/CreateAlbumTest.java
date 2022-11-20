package com.example.vinilos;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import static org.hamcrest.Matchers.allOf;
import com.example.vinilos.ui.MainActivity;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAlbumTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void successfulCreateAlbum() throws InterruptedException {

        ViewInteraction AlbumButton = onView(allOf(withId(R.id.albumsListButton)));
        AlbumButton.perform(click());
        Thread.sleep(10000);

        ViewInteraction createAlbumButton = onView(allOf(withId(R.id.album_form_button)));
        createAlbumButton.perform(click());

        ViewInteraction nameField = onView(allOf(withId(R.id.AlbumNameEditText)));

        String currentDate = new java.util.Date().toString();
        nameField.perform(typeText("Album Test 1 "));

        ViewInteraction coverField = onView(allOf(withId(R.id.AlbumCoverEditText)));
        coverField.perform(scrollTo(), replaceText("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"));
        Thread.sleep(1000);
        onView(isRoot()).perform(pressBack());

        ViewInteraction releaseDateField = onView(allOf(withId(R.id.AlbumReleaseDateEditText)));
        releaseDateField.perform(typeText("12/04/2022"));
        Thread.sleep(1000);
        onView(isRoot()).perform(pressBack());

        ViewInteraction descriptionField = onView(allOf(withId(R.id.AlbumDescriptionEditText)));
        descriptionField.perform(scrollTo(), replaceText("Buscando América es el primer álbum"));
        Thread.sleep(1000);

        ViewInteraction genreField = onView(allOf(withId(R.id.AlbumGenreEditText)));
        genreField.perform(scrollTo(),replaceText("Salsa"));
        Thread.sleep(1000);

        ViewInteraction recordLabelField = onView(allOf(withId(R.id.AlbumRecordLabelEditText)));
        recordLabelField.perform(scrollTo(),replaceText("Elektra"));
        Thread.sleep(1000);

        ViewInteraction confirmCreateAlbumButton = onView(allOf(withId(R.id.create_album_button)));
        confirmCreateAlbumButton.perform(scrollTo(), click());

        Espresso.pressBack();

        Thread.sleep(1000);
    }
}

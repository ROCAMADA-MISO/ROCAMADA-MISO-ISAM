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
public class CreatePrizeTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void successfulCreatePrize() throws InterruptedException {
        ViewInteraction prizeButton = onView(allOf(withId(R.id.prizesList), withText("Premios")));
        prizeButton.perform(click());

        ViewInteraction createPrizeButton = onView(allOf(withId(R.id.prize_form_button)));
        createPrizeButton.perform(click());

        ViewInteraction nameField = onView(allOf(withId(R.id.awardNameEditText)));

        String currentDate = new java.util.Date().toString();
        nameField.perform(typeText("Premio a mejor artista independiente " + currentDate));

        ViewInteraction organizationField = onView(allOf(withId(R.id.awardOrganizationEditText)));
        organizationField.perform(typeText("Grammy's"));

        ViewInteraction descriptionField = onView(allOf(withId(R.id.awardDescriptionEditText)));
        descriptionField.perform(typeText("Este es un premio al mejor artista independiente del anio 2022"));

        ViewInteraction confirmCreatePrizeButton = onView(allOf(withId(R.id.create_prize_button)));
        confirmCreatePrizeButton.perform(click());

        Espresso.pressBack();

        Thread.sleep(10000);

        onView(withText("Premio a mejor artista independiente " + currentDate)).check(matches(isDisplayed()));
    }
}

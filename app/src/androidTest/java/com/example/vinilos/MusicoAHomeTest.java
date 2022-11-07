package com.example.vinilos;

        import androidx.test.filters.LargeTest;
        import com.example.vinilos.ui.MainActivity;
        import androidx.test.ext.junit.runners.AndroidJUnit4;
        import static androidx.test.espresso.Espresso.onView;
        import androidx.test.ext.junit.rules.ActivityScenarioRule;
        import static androidx.test.espresso.action.ViewActions.click;
        import static androidx.test.espresso.action.ViewActions.pressBack;
        import static androidx.test.espresso.assertion.ViewAssertions.matches;
        import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
        import static androidx.test.espresso.matcher.ViewMatchers.withId;
        import static androidx.test.espresso.matcher.ViewMatchers.withText;



        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MusicoAHomeTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void listMusician() throws InterruptedException {
        onView(withId(R.id.musicianListButton))
                .perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withText("Home")).check(matches(isDisplayed()));
    }
}
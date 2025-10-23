package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<>(MainActivity.class);

    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
    }

    /** 1) Check whether the activity correctly switched */
    @Test
    public void switchesToShowActivityOnListClick() {
        try { onView(withId(R.id.button_clear)).perform(click()); } catch (Exception ignored) {}
        addCity("Edmonton");

        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.text_city)).check(matches(isDisplayed()));
    }

    /** 2) Test whether the city name is consistent */
    @Test
    public void showsCorrectCityName() {
        try { onView(withId(R.id.button_clear)).perform(click()); } catch (Exception ignored) {}
        addCity("Vancouver");

        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.text_city)).check(matches(withText("Vancouver")));
    }

    /** 3) Test the "back" button */
    @Test
    public void backButtonReturnsToMainActivity() {
        try { onView(withId(R.id.button_clear)).perform(click()); } catch (Exception ignored) {}
        addCity("Calgary");

        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.button_back)).perform(click());

        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}

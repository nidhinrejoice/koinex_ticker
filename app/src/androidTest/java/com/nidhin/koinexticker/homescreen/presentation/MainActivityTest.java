package com.nidhin.koinexticker.homescreen.presentation;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiSelector;

import com.nidhin.koinexticker.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasTextColor;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mDevice =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    UiDevice mDevice;

    @Test
    public void testFetchPrices() {

        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.toolbar)).check(matches(withText("Achain")));
        onView(withId(R.id.percent_change)).check(matches(withText("-3.66%")));
        onView(withId(R.id.percent_change)).check(matches(hasTextColor(R.color.red)));
        onView(withId(R.id.go_back)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.percent_change)).check(matches(hasTextColor(R.color.green)));
        onView(withId(R.id.go_back)).perform(click());
        SystemClock.sleep(3000);
        testSort();
    }

    @Test
    public void testSort() {

        onView(withId(R.id.sort)).perform(click());
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.toolbar)).check(matches(withText("Bitcoin")));
        SystemClock.sleep(3000);
    }

}
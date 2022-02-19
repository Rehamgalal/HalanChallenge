package com.example.halanchallenge;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.halanchallenge.ui.fragments.LoginFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestLoginFragment {

    @IdRes
    private final int theme = R.style.Theme_AppCompat_DayNight_NoActionBar;

    @Before
    public void setup() {
        Bundle args = new Bundle();
        FragmentScenario.launchInContainer(LoginFragment.class,args, theme, Lifecycle.State.STARTED);
    }
    @Test
    public void testUsernameLength() {

                onView(withId(R.id.username_et)).perform(typeText("name"), ViewActions.closeSoftKeyboard());
                onView(withId(R.id.login_button)).perform(click());
                onView(withId(android.R.id.message)).inRoot(isToast()).check(matches(withText("برجاء ملئ كافة البيانات، اسم المستخدم من ٦ الى ١٥ حرف")));
                onView(withId(R.id.username_et)).perform(typeText("nameisoversixteencharachter"), ViewActions.closeSoftKeyboard());
                onView(withId(R.id.login_button)).perform(click());
                onView(withId(android.R.id.message)).inRoot(isToast()).check(matches(withText("برجاء ملئ كافة البيانات، اسم المستخدم من ٦ الى ١٥ حرف")));

    }

    public static Matcher<Root> isToast() {
        return new TypeSafeMatcher<Root>() {
            @Override
            protected boolean matchesSafely(Root item) {
                int type = item.getWindowLayoutParams().get().type;
                if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                    IBinder windowToken = item.getDecorView().getWindowToken();
                    IBinder appToken = item.getDecorView().getApplicationWindowToken();
                    return windowToken == appToken;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is toast");
            }
        };
    }
}

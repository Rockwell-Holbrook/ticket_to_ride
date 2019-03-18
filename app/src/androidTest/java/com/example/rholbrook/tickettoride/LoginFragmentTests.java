package com.example.rholbrook.tickettoride;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResourceTimeoutException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.example.rholbrook.tickettoride.authentication.AuthenticationActivity;
import com.example.rholbrook.tickettoride.main.Authentication;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginFragmentTests {

    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityTestRule = new ActivityTestRule<>(AuthenticationActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(AuthenticationActivity.getIdlingResourceInTest());
    }

    @Test
    public void registerButtonTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.register_button), withText("Not a User? Register Here"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView), withText("Register Your Account"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.authentication_fragment_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));
    }

    @Test
    public void successfulLoginTest() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.login_username_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.login_password_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        ViewInteraction mainActivityIndicator = onView(
                allOf(withId(R.id.lobby)));

        usernameEditText.perform(typeText("bob")).perform(closeSoftKeyboard());
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        loginButton.perform(click());

        mainActivityIndicator.check(matches(isDisplayed()));
    }

    @Test
    public void failedLoginTest() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.login_username_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.login_password_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));

        usernameEditText.perform(typeText("username")).perform(closeSoftKeyboard());
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        loginButton.perform(click());

        onView(withText(R.string.invalid_login)).
                inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));

        usernameEditText.perform(clearText());
        passwordEditText.perform(clearText());
        usernameEditText.perform(typeText("bob")).perform(closeSoftKeyboard());
        passwordEditText.perform(typeText("pswd")).perform(closeSoftKeyboard());
        loginButton.perform(click());

        onView(withText(R.string.invalid_login)).
                inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void loginButtonDisabledTest() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.login_username_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.login_password_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));

        //If both editTexts are empty, login Button is disabled
        loginButton.check(matches(not(isEnabled())));

        //If password editText is empty, but username is not, login Button is disabled
        usernameEditText.perform(typeText("username")).perform(closeSoftKeyboard());
        loginButton.check(matches(not(isEnabled())));

        //If username editText is empty, but password is not, login Button is disabled
        usernameEditText.perform(clearText());
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        loginButton.check(matches(not(isEnabled())));

        //If both editTexts are not empty, the login Button is enabled
        usernameEditText.perform(typeText("username")).perform(closeSoftKeyboard());
        loginButton.check(matches(isEnabled()));

        //Test to see if it becomes re-disabled
        usernameEditText.perform(clearText());
        loginButton.check(matches(not(isEnabled())));
    }

    @After
    public void unRegisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(AuthenticationActivity.getIdlingResourceInTest());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

package com.example.rholbrook.tickettoride;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.example.rholbrook.tickettoride.authentication.AuthenticationActivity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterFragmentTests {

    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityTestRule = new ActivityTestRule<>(AuthenticationActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(AuthenticationActivity.getIdlingResourceInTest());
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.register_button), withText("Not a User? Register Here"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatTextView.perform(click());
    }


    @Test
    public void registerButtonDisabledTest() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.register_username_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.register_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction confPasswordEditText = onView(
                allOf(withId(R.id.register_conf_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        ViewInteraction registerButton = onView(
                allOf(withId(R.id.register_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                4),
                        isDisplayed()));

        //If all editTexts are empty, register Button is disabled
        registerButton.check(matches(not(isEnabled())));

        //Username not empty
        usernameEditText.perform(typeText("username")).perform(closeSoftKeyboard());
        registerButton.check(matches(not(isEnabled())));

        //Username and password not empty
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        registerButton.check(matches(not(isEnabled())));

        //All fields are filled
        confPasswordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        registerButton.check(matches(isEnabled()));

        //Password and conf password are not empty
        usernameEditText.perform(clearText());
        registerButton.check(matches(not(isEnabled())));

        //Password Not empty
        confPasswordEditText.perform(clearText());
        registerButton.check(matches(not(isEnabled())));

        //Conf not empty
        passwordEditText.perform(clearText());
        confPasswordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        registerButton.check(matches(not(isEnabled())));

        //Conf and username not empty
        usernameEditText.perform(typeText("username")).perform(closeSoftKeyboard());
        registerButton.check(matches(not(isEnabled())));
    }

    @Test
    public void returnToLogin() {
        mActivityTestRule.getActivity().getSupportFragmentManager().popBackStack();
        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));

        loginButton.check(matches(isDisplayed()));
    }

    @Test
    public void notMatchingPassword() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.register_username_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.register_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction confPasswordEditText = onView(
                allOf(withId(R.id.register_conf_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        ViewInteraction registerButton = onView(
                allOf(withId(R.id.register_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                4),
                        isDisplayed()));

        usernameEditText.perform(typeText("username")).perform(closeSoftKeyboard());
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        confPasswordEditText.perform(typeText("password1")).perform(closeSoftKeyboard());

        registerButton.perform(click());

        onView(withText(R.string.passwords_dont_match)).
                inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void repeatRegister() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.register_username_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.register_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction confPasswordEditText = onView(
                allOf(withId(R.id.register_conf_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        ViewInteraction registerButton = onView(
                allOf(withId(R.id.register_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                4),
                        isDisplayed()));

        usernameEditText.perform(typeText("bob")).perform(closeSoftKeyboard());
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        confPasswordEditText.perform(typeText("password")).perform(closeSoftKeyboard());

        registerButton.perform(click());

        onView(withText(R.string.repeat_register)).
                inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void successfulRegister() {
        ViewInteraction usernameEditText = onView(
                allOf(withId(R.id.register_username_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        ViewInteraction passwordEditText = onView(
                allOf(withId(R.id.register_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        ViewInteraction confPasswordEditText = onView(
                allOf(withId(R.id.register_conf_password_field),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        ViewInteraction registerButton = onView(
                allOf(withId(R.id.register_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.authentication_fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        ViewInteraction mainActivityIndicator = onView(
                allOf(withId(R.id.lobby)));

        String randomUsername = UUID.randomUUID().toString();
        usernameEditText.perform(typeText(randomUsername)).perform(closeSoftKeyboard());
        passwordEditText.perform(typeText("password")).perform(closeSoftKeyboard());
        confPasswordEditText.perform(typeText("password")).perform(closeSoftKeyboard());

        registerButton.perform(click());
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

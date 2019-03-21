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
import com.example.rholbrook.tickettoride.gamelobby.GameLobbyActivity;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.shared.model.User;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GameLobbyActivityTests {

    @Rule
    public ActivityTestRule<GameLobbyActivity> mActivityTestRule = new ActivityTestRule<>(GameLobbyActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(AuthenticationActivity.getIdlingResourceInTest());
        Authentication.getInstance().setUsername("bob");
        try {
            AuthenticationServerProxy.getInstance().login(new User("bob", "password"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        IdlingRegistry.getInstance().unregister(AuthenticationActivity.getIdlingResourceInTest());
        IdlingRegistry.getInstance().register(MainActivity.getIdlingResourceInTest());
    }

    @Test
    public void joinGameDisabledTest() {
        ViewInteraction joinGameButton = onView(allOf(withText(R.string.join_game)));
        joinGameButton.check(matches(not(isEnabled())));
    }

    @Test
    public void createGameDialogTest() {
        ViewInteraction createGameButton = onView(allOf(withText(R.string.create_game)));
        createGameButton.perform(click());

        ViewInteraction dialog = onView(withId(R.id.textView3)).inRoot(isDialog());
        dialog.check(matches(isDisplayed()));

        ViewInteraction okButton = onView(allOf(withText(R.string.OK))).inRoot(isDialog());

        //Check create game without title
        ViewInteraction gameName = onView(allOf(withId(R.id.game_name_edit_text))).inRoot(isDialog());
        gameName.perform(clearText());
        okButton.perform(click());

        onView(withText(R.string.name_not_specified)).
                inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void joinGameDialogTest() {
        ViewInteraction joinGameButton = onView(allOf(withText(R.string.join_game)));
        joinGameButton.perform(click());
    }

    @After
    public void unRegisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(MainActivity.getIdlingResourceInTest());
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

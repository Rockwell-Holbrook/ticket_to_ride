package com.example.rholbrook.tickettoride;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.example.rholbrook.tickettoride.assertions.RecyclerViewItemCountAssertion;
import com.example.rholbrook.tickettoride.authentication.AuthenticationActivity;
import com.example.rholbrook.tickettoride.finishgame.FinishGameActivity;
import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.shared.model.City;
import com.example.shared.model.Player;
import com.example.shared.model.Ticket;
import com.example.shared.model.User;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.ArrayList;

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
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FinishGameActivityTests {

    private static GameActivityModel mModel;
    private static ArrayList<Player> players;

    @Rule
    public ActivityTestRule<FinishGameActivity> mActivityTestRule = new ActivityTestRule<>(FinishGameActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(FinishGameActivity.getIdlingResourceInTest());
        Authentication.getInstance().setUsername("bob");
    }

    @BeforeClass
    public static void setPlayers() {
        players = new ArrayList<>();
        mModel = GameActivityModel.getInstance();
        createPlayerOne("usernameOne", Player.PlayerColor.BLACK);
        createPlayerTwo("usernameTwo", Player.PlayerColor.BLUE);
        createPlayerThree("usernameThree", Player.PlayerColor.RED);
        createPlayerFour("usernameFour", Player.PlayerColor.YELLOW);
        createWinner("usernameFive", Player.PlayerColor.GREEN);
    }

    private static void createWinner(String usernameFive, Player.PlayerColor color) {
        Player player = new Player(usernameFive, true, color);
        player.setPointsEarned(120);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        Ticket incompleteTicketTwo = new Ticket(3, new City("Sault Ste Marie"), new City("Nashville"), 8);
        Ticket completedTicketTwo = new Ticket(3, new City("Sault Ste Marie"), new City("Nashville"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        incompleteTicketTwo.setCompleted(false);
        completedTicketTwo.setCompleted(true);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        player.addTicket(incompleteTicketTwo);
        player.addTicket(completedTicketTwo);
        mModel.setOpponentFour(player);
        players.add(player);
    }

    private static void createPlayerOne(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        player.setLongestRouteCount(45);
        mModel.setClient(player);
        players.add(player);
    }

    private static void createPlayerTwo(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        mModel.setOpponentOne(player);
        players.add(player);
    }

    private static void createPlayerThree(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        mModel.setOpponentTwo(player);
        players.add(player);
    }

    private static void createPlayerFour(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        mModel.setOpponentThree(player);
        players.add(player);
    }

    @Test
    public void testPlayers() {
        onView(withId(R.id.finish_game_players)).check(new RecyclerViewItemCountAssertion(players.size()));

        //Test left most player
        assertTrue(players.get(0).getCompletedCount() == 1);
        assertTrue(players.get(0).getIncompletedTicketCount() == 1);
        assertTrue(players.get(0).getTotalPoints() == 73);
        assertTrue(players.get(0).isHasLongestRoute() == true);
        assertTrue(players.get(0).isHasGlobeTrotter() == false);
        assertTrue(players.get(0).getBonusPoints() == 10);

        //Test middle player
        assertTrue(players.get(2).getCompletedCount() == 1);
        assertTrue(players.get(2).getIncompletedTicketCount() == 1);
        assertTrue(players.get(2).getTotalPoints() == 63);
        assertTrue(players.get(2).isHasLongestRoute() == false);
        assertTrue(players.get(2).isHasGlobeTrotter() == false);
        assertTrue(players.get(2).getBonusPoints() == 0);

        //Test winner
        assertTrue(players.get(4).getCompletedCount() == 2);
        assertTrue(players.get(4).getIncompletedTicketCount() == 2);
        assertTrue(players.get(4).getTotalPoints() == 148);
        assertTrue(players.get(4).isHasLongestRoute() == false);
        assertTrue(players.get(4).isHasGlobeTrotter() == true);
        assertTrue(players.get(4).getBonusPoints() == 15);
    }

    @Test
    public void testCloseButton() {
        ViewInteraction closeButton = onView(withId(R.id.finish_close_button));
        closeButton.perform(click());

        onView(withText(R.string.join_game)).check(matches(isDisplayed()));
    }

    @After
    public void unRegisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(FinishGameActivity.getIdlingResourceInTest());
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

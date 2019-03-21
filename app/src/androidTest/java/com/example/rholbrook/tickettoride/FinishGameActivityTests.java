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
import com.example.rholbrook.tickettoride.finishgame.FinishGameActivity;
import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
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
        Ticket completedTicket = new Ticket(2, "Calgary", "Phoenix", 10);
        Ticket incompleteTicket = new Ticket(1, "Boston", "Miami", 10);
        Ticket incompleteTicketTwo = new Ticket(3, "Calgary", "Salt_Lake_City", 10);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        incompleteTicketTwo.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        player.addTicket(incompleteTicketTwo);
        mModel.setOpponentFour(player);
        players.add(player);
    }

    private static void createPlayerOne(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(2, "Calgary", "Phoenix", 10);
        Ticket incompleteTicket = new Ticket(1, "Boston", "Miami", 10);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        mModel.setClient(player);
        players.add(player);
    }

    private static void createPlayerTwo(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(2, "Calgary", "Phoenix", 10);
        Ticket incompleteTicket = new Ticket(1, "Boston", "Miami", 10);
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
        Ticket completedTicket = new Ticket(2, "Calgary", "Phoenix", 10);
        Ticket incompleteTicket = new Ticket(1, "Boston", "Miami", 10);
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
        Ticket completedTicket = new Ticket(2, "Calgary", "Phoenix", 10);
        Ticket incompleteTicket = new Ticket(1, "Boston", "Miami", 10);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        mModel.setOpponentThree(player);
        players.add(player);
    }

    @Test
    public void testPlayers() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    TICKET_IMAGE_MAP = new HashMap<Integer, Integer>();
//        TICKET_IMAGE_MAP.put(Integer.valueOf(1), R.mipmap.boston_miami);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(2), R.mipmap.calgary_phoenix);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(3), R.mipmap.calgary_salt_lake_city);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(4), R.mipmap.chicago_new_orleans);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(5), R.mipmap.chicago_santa_fe);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(6), R.mipmap.dallas_new_york);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(7), R.mipmap.denver_el_paso);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(8), R.mipmap.denver_pittsburgh);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(9), R.mipmap.duluth_el_paso);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(10), R.mipmap.duluth_houston);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(11), R.mipmap.helena_los_angeles);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(12), R.mipmap.kansas_city_houston);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(13), R.mipmap.los_angeles_chicago);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(14), R.mipmap.los_angeles_miami);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(15), R.mipmap.los_angeles_new_york);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(16), R.mipmap.montreal_atlanta);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(17), R.mipmap.montreal_new_orleans);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(18), R.mipmap.new_york_atlanta);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(19), R.mipmap.portland_nashville);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(20), R.mipmap.portland_phoenix);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(21), R.mipmap.san_francisco_atlanta);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(22), R.mipmap.sault_st_marie_nashville);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(23), R.mipmap.sault_st_marie_oaklahoma_city);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(24), R.mipmap.seattle_los_angeles);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(25), R.mipmap.seattle_new_york);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(26), R.mipmap.toronto_miami);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(27), R.mipmap.vancouver_montreal);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(28), R.mipmap.vancouver_santa_fe);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(29), R.mipmap.winnipeg_houston);
//        TICKET_IMAGE_MAP.put(Integer.valueOf(30), R.mipmap.winnipeg_little_rock);

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

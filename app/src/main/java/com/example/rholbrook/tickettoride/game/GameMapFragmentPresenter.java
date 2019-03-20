package com.example.rholbrook.tickettoride.game;

import android.widget.Button;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Player;
import com.example.shared.model.Route;
import com.example.shared.model.TrainCard;

import java.util.*;

/**
 * The GameMapFragmentPresenter class is the presenter for the GameMapFragment view. It acts as the middle-man between the view and model classes.
 *
 * This class implements the GameMapFragmentContract.Presenter interface as well as Java's Observer class.
 *
 * @invariant availableButtons must be a non-null List of type Button
 * @invariant viewCallback must be a non-null GameMapFragmentContract.View implementation
 */
public class GameMapFragmentPresenter implements GameMapFragmentContract.Presenter, Observer {

    /**
     * Callback to the GameMapFragment View class
     */
    private GameMapFragmentContract.View viewCallback;

    /**
     * Instance of the GameActivityModel model class
     */
    private GameActivityModel mModel;

    /**
     * List of Button widgets.
     * This list will be assigned during every client turn.
     * The list of buttons are used to enable and disable click listeners for the buttons
     */
    private List<Button> availableButtons;


    /**
     * Constructor for the GameMapFragmentPresenter
     *
     * Sets the view callback
     * Initializes the availableButtons list
     * Defines the mModel attribute
     * Sets the presenter callback in the model class
     * Adds this instance of the class as an observer of the model class
     *
     * @param viewCallback instance of the view to be used as the callback
     *
     * @pre viewCallback must be a non-null GameMapFragmentContract.View object
     *
     * @post this instance of the GameMapFragmentPresenter must be listed in GameActivityModel's observables
     * @post GameActiviyModel's gameMapFragmentPresenter must not be null
     * @post availableButtons must not be null
     * @post viewCallback must not be null
     */
    public GameMapFragmentPresenter(GameMapFragmentContract.View viewCallback) {
        this.viewCallback = viewCallback;
        availableButtons = new ArrayList<>();
        mModel = GameActivityModel.getInstance();
        mModel.setGameMapFragmentPresenter(this);
        mModel.addObserver(this);
    }

    /**
     * Update Method for the Observer
     *
     * Receives updated information from the observable
     *
     * If the argument is a boolean object, that means that the isTurn attribute has changed
     * If isTurn has changed, then enable buttons
     * Otherwise, then disable buttons
     *
     * @param o Model object that acts as the observer
     * @param arg Additional arguments that the observable sends
     *
     * @pre o must be a reference to an Observable that this instance is an observer of
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().getName().equals(Boolean.class.getName())) {
            boolean isTurn = (boolean) arg;
            if (isTurn) {
                viewCallback.startUserTurn(availableButtons);
            } else {
                viewCallback.endUserTurn(availableButtons);
            }
        }
    }

    /**
     * Route Claimed method for the GameMapFragmentPresenter
     *
     * Called when the model receives a call from the server
     * Instructs the view to change the color of the buttons for a specific route
     *
     * @param player Player object of the one who claimed the route
     * @param route Route object of the route that was claimed
     *
     * @pre player must be a non-null Player object
     * @pre player must have the same username as one of the Player objects stored in the GameActivityModel
     * @pre route must be a non-null Route object
     * @pre route must have a routeId between 1 and 100, or as found in the ROUTE_GROUP_MAP
     */
    @Override
    public void routeClaimed(Player player, Route route) {
        viewCallback.routeClaimed(getPlayerColor(player), ROUTE_GROUP_MAP.get(route.getGroupId()));
    }

    @Override
    public List<TrainCard> getPlayerHand() {
        return mModel.getClient().getTrainCards();
    }

    @Override
    public void selectingCards() {
        viewCallback.endUserTurn(availableButtons);
    }

    /**
     * UpdateAvailableRoutes method for the GameMapFragmentPresenter
     *
     * Called when the model receives a new set of availableRoutes
     * Clears the availableButtons list
     * Sends the group to the view to add click listeners
     *
     * @param availableRoutes List of routes that the player can take
     *
     * @pre availableRoutes must be a non-null list of Routes
     * @pre availableRoutes may be an empty list
     *
     * @post availableButtons is a non-null list of Buttons
     * @post availableButtons is an empty list
     * @post each button associated with the Group found from the ROUTE_GROUP_MAP in the view must have an onClickListener
     */
    @Override
    public void updateAvailableRoutes(List<Route> availableRoutes) {
        availableButtons.clear();
        for (Route route : availableRoutes) {
            viewCallback.addClickListeners(ROUTE_GROUP_MAP.get(route.getGroupId()), route.getGroupId());
        }
    }

    /**
     * SelectRoute method for the GameMapFragmentPresenter
     *
     * Sends the routeId of the route the user selected to the model
     *
     * @param routeId Id of the Route object the user selected
     *
     * @pre routeId must be an integer between 1 and 100, as found in the ROUTE_GROUP_MAP
     */
    @Override
    public void selectRoute(int routeId) {
        viewCallback.endUserTurn(availableButtons);
        mModel.endUserTurn();
    }

    /**
     * AddAvailbleButton Method for GameMapFragmentPresenter
     *
     * Adds a button object to the availableButtons list
     *
     * @param button Button object to add to the availableButtons list
     *
     * @pre button must be a button object
     *
     * @post availableButtons must have a length one greater than before
     * @post availableButtons must contain a Button object that corresponds with button
     */
    @Override
    public void addAvailableButton(Button button) {
        availableButtons.add(button);
    }

    /**
     * Static map of the possible routeIds and their corresponding Group ids as found in the layout file
     */
    public static final Map<Integer, Integer> ROUTE_GROUP_MAP;
    static {
        ROUTE_GROUP_MAP = new HashMap<Integer, Integer>();
        ROUTE_GROUP_MAP.put(1, R.id.seattle_calgary_group_one);
        ROUTE_GROUP_MAP.put(2, R.id.san_francisco_salt_lake_city_group_two);
        ROUTE_GROUP_MAP.put(3, R.id.san_francisco_portland_group_one);
        ROUTE_GROUP_MAP.put(4, R.id.portland_seattle_group_one);
        ROUTE_GROUP_MAP.put(5, R.id.los_angeles_las_vegas_group_one);
        ROUTE_GROUP_MAP.put(6, R.id.vancouver_calgary_group_one);
        ROUTE_GROUP_MAP.put(7, R.id.portland_seattle_group_two);
        ROUTE_GROUP_MAP.put(8, R.id.seattle_vancouver_group_two);
        ROUTE_GROUP_MAP.put(9, R.id.seattle_vancouver_group_one);
        ROUTE_GROUP_MAP.put(10, R.id.los_angeles_san_francisco_group_one);
        ROUTE_GROUP_MAP.put(11, R.id.los_angeles_san_francisco_group_two);
        ROUTE_GROUP_MAP.put(12, R.id.las_vegas_salt_lake_city_group_one);
        ROUTE_GROUP_MAP.put(13, R.id.san_francisco_salt_lake_city_group_one);
        ROUTE_GROUP_MAP.put(14, R.id.san_francisco_portland_group_two);
        ROUTE_GROUP_MAP.put(15, R.id.portland_salt_lake_city_group_one);
        ROUTE_GROUP_MAP.put(16, R.id.seattle_helena_group_one);
        ROUTE_GROUP_MAP.put(17, R.id.los_angeles_phoenix_group_one);
        ROUTE_GROUP_MAP.put(18, R.id.calgary_helena_group_one);
        ROUTE_GROUP_MAP.put(19, R.id.salt_lake_city_helena_group_one);
        ROUTE_GROUP_MAP.put(20, R.id.salt_lake_city_denver_group_one);
        ROUTE_GROUP_MAP.put(21, R.id.salt_lake_city_denver_group_two);
        ROUTE_GROUP_MAP.put(22, R.id.los_angeles_el_paso_group_one);
        ROUTE_GROUP_MAP.put(23, R.id.phoenix_el_paso_group_one);
        ROUTE_GROUP_MAP.put(24, R.id.phoenix_denver_group_one);
        ROUTE_GROUP_MAP.put(25, R.id.pheonix_santa_fe_group_one);
        ROUTE_GROUP_MAP.put(26, R.id.denver_helena_group_one);
        ROUTE_GROUP_MAP.put(27, R.id.calgary_winnipeg_group_one);
        ROUTE_GROUP_MAP.put(28, R.id.denver_santa_fe_group_one);
        ROUTE_GROUP_MAP.put(29, R.id.santa_fe_el_paso_group_one);
        ROUTE_GROUP_MAP.put(30, R.id.helena_winnipeg_group_one);
        ROUTE_GROUP_MAP.put(31, R.id.helena_deluth_group_one);
        ROUTE_GROUP_MAP.put(32, R.id.helena_omaha_group_one);
        ROUTE_GROUP_MAP.put(33, R.id.denver_omaha_group_one);
        ROUTE_GROUP_MAP.put(34, R.id.winnipeg_duluth_group_one);
        ROUTE_GROUP_MAP.put(35, R.id.santa_fe_oklahoma_city_group_one);
        ROUTE_GROUP_MAP.put(36, R.id.denver_kansas_city_group_two);
        ROUTE_GROUP_MAP.put(37, R.id.denver_kansas_city_group_one);
        ROUTE_GROUP_MAP.put(38, R.id.denver_oklahoma_city_group_one);
        ROUTE_GROUP_MAP.put(39, R.id.el_paso_oklahoma_city_group_one);
        ROUTE_GROUP_MAP.put(40, R.id.el_paso_dallas_group_one);
        ROUTE_GROUP_MAP.put(41, R.id.el_paso_houston_group_one);
        ROUTE_GROUP_MAP.put(42, R.id.winnipeg_sault_st_marie_group_one);
        ROUTE_GROUP_MAP.put(43, R.id.duluth_sault_st_marie_group_one);
        ROUTE_GROUP_MAP.put(44, R.id.duluth_omaha_group_one);
        ROUTE_GROUP_MAP.put(45, R.id.duluth_omaha_group_two);
        ROUTE_GROUP_MAP.put(46, R.id.omaha_kansas_city_group_one);
        ROUTE_GROUP_MAP.put(47, R.id.omaha_kansas_city_group_two);
        ROUTE_GROUP_MAP.put(48, R.id.kansas_city_oklahoma_city_group_one);
        ROUTE_GROUP_MAP.put(49, R.id.kansas_city_oklahoma_city_group_two);
        ROUTE_GROUP_MAP.put(50, R.id.oklahoma_city_dallas_group_one);
        ROUTE_GROUP_MAP.put(51, R.id.oklahoma_city_dallas_group_two);
        ROUTE_GROUP_MAP.put(52, R.id.dallas_houston_group_one);
        ROUTE_GROUP_MAP.put(53, R.id.dallas_houston_group_two);
        ROUTE_GROUP_MAP.put(54, R.id.houston_new_orleans_group_one);
        ROUTE_GROUP_MAP.put(55, R.id.dallas_little_rock_group_one);
        ROUTE_GROUP_MAP.put(56, R.id.oklahoma_city_little_rock_group_one);
        ROUTE_GROUP_MAP.put(57, R.id.kansas_city_saint_louis_group_one);
        ROUTE_GROUP_MAP.put(58, R.id.kansas_city_saint_louis_group_two);
        ROUTE_GROUP_MAP.put(59, R.id.omaha_chicago_group_one);
        ROUTE_GROUP_MAP.put(60, R.id.duluth_chicago_group_one);
        ROUTE_GROUP_MAP.put(61, R.id.duluth_toronto_group_one);
        ROUTE_GROUP_MAP.put(62, R.id.sault_st_marie_toronto_group_one);
        ROUTE_GROUP_MAP.put(63, R.id.sault_st_marie_montreal_group_one);
        ROUTE_GROUP_MAP.put(64, R.id.pittsburgh_raleigh_group_one);
        ROUTE_GROUP_MAP.put(65, R.id.charleston_miami_group_one);
        ROUTE_GROUP_MAP.put(66, R.id.saint_louis_chicago_group_two);
        ROUTE_GROUP_MAP.put(67, R.id.saint_louis_chicago_group_one);
        ROUTE_GROUP_MAP.put(68, R.id.new_orleans_atlanta_group_one);
        ROUTE_GROUP_MAP.put(69, R.id.new_orleans_atlanta_group_two);
        ROUTE_GROUP_MAP.put(70, R.id.little_rock_nashville_group_one);
        ROUTE_GROUP_MAP.put(71, R.id.little_rock_saint_louis_group_one);
        ROUTE_GROUP_MAP.put(72, R.id.pittsburgh_washington_group_one);
        ROUTE_GROUP_MAP.put(73, R.id.new_york_washington_group_one);
        ROUTE_GROUP_MAP.put(74, R.id.new_york_washington_group_two);
        ROUTE_GROUP_MAP.put(75, R.id.montreal_new_york_group_one);
        ROUTE_GROUP_MAP.put(76, R.id.montreal_boston_group_two);
        ROUTE_GROUP_MAP.put(77, R.id.montreal_boston_group_one);
        ROUTE_GROUP_MAP.put(78, R.id.pittsburgh_new_york_group_two);
        ROUTE_GROUP_MAP.put(79, R.id.raleigh_washington_group_two);
        ROUTE_GROUP_MAP.put(80, R.id.atlanta_raleigh_group_one);
        ROUTE_GROUP_MAP.put(81, R.id.atlanta_raleigh_group_two);
        ROUTE_GROUP_MAP.put(82, R.id.raleigh_washington_group_one);
        ROUTE_GROUP_MAP.put(83, R.id.pittsburgh_new_york_group_one);
        ROUTE_GROUP_MAP.put(84, R.id.new_orleans_little_rock_group_one);
        ROUTE_GROUP_MAP.put(85, R.id.toronto_pittsburgh_group_one);
        ROUTE_GROUP_MAP.put(86, R.id.chicago_pittsburgh_group_two);
        ROUTE_GROUP_MAP.put(87, R.id.nashville_pittsburgh_group_one);
        ROUTE_GROUP_MAP.put(88, R.id.nashville_raleigh_group_one);
        ROUTE_GROUP_MAP.put(89, R.id.raleigh_charleston_group_one);
        ROUTE_GROUP_MAP.put(90, R.id.saint_louis_pittsburgh_group_one);
        ROUTE_GROUP_MAP.put(91, R.id.saint_louis_nashville_group_one);
        ROUTE_GROUP_MAP.put(92, R.id.atlanta_miami_group_one);
        ROUTE_GROUP_MAP.put(93, R.id.new_orleans_miami_group_one);
        ROUTE_GROUP_MAP.put(94, R.id.atlanta_charleston_group_one);
        ROUTE_GROUP_MAP.put(95, R.id.nashville_atlanta_group_one);
        ROUTE_GROUP_MAP.put(96, R.id.chicago_pittsburgh_group_one);
        ROUTE_GROUP_MAP.put(97, R.id.boston_new_york_group_two);
        ROUTE_GROUP_MAP.put(98, R.id.boston_new_york_group_one);
        ROUTE_GROUP_MAP.put(99, R.id.chicago_toronto_group_one);
        ROUTE_GROUP_MAP.put(100, R.id.toronto_montreal_group_one);
    }

    /**
     * GetPlayerColor method for the GameMapFragmentPresenter
     *
     * Returns the color that the Player corresponds to
     *
     * @param player Player object that the user needs to find the color of
     * @return integer representation of the color value that the player's color corresponds to
     *
     * @pre player must be a non-null Player object
     * @pre player must have the same username as one of the Player objects stored in the GameActivityModel
     * @pre player msut have a non-null color attribute
     */
    public int getPlayerColor(Player player) {
        switch (player.getPlayerColor()) {
            case YELLOW:
                return R.color.yellow;
            case GREEN:
                return R.color.green;
            case BLACK:
                return R.color.grey;
            case BLUE:
                return R.color.blue;
            case RED:
                return R.color.red;
            default:
                return 0;
        }
    }
}

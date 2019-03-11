package com.example.rholbrook.tickettoride.game;

import android.support.constraint.Group;
import android.widget.Button;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Player;
import com.example.shared.model.Route;

import java.util.*;

public class GameMapFragmentPresenter implements GameMapFragmentContract.Presenter, Observer {
    private GameMapFragmentContract.View viewCallback;
    private GameActivityModel mModel;
    private List<Button> availableButtons;

    public GameMapFragmentPresenter(GameMapFragmentContract.View viewCallback) {
        this.viewCallback = viewCallback;
        mModel = GameActivityModel.getInstance();
        mModel.setGameMapFragmentPresenter(this);
        mModel.addObserver(this);
        availableButtons = new ArrayList<>();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void init() {

    }

    @Override
    public void startUserTurn() {
        viewCallback.startUserTurn(availableButtons);
    }

    @Override
    public void updateAvailableRoutes(List<Route> availableRoutes) {
        availableButtons.clear();
        for (Route route : availableRoutes) {
            viewCallback.addClickListeners(ROUTE_GROUP_MAP.get(route.getGroupId()));
        }
    }

    @Override
    public void selectRoute(int routeId) {
        mModel.selectRoute(routeId);
    }

    @Override
    public void addAvailableButton(Button button) {
        availableButtons.add(button);
    }

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
}

package com.example.shared.model;

import java.util.HashMap;
import java.util.Map;

public class Route {
    private int groupId;
    private City cityOne;
    private City cityTwo;
    private RouteColor color;
    private int pointValue;
    private int length;
    private boolean hasAdjacentRoute;
    private int adjacentRouteId;

    public Route(int groupId, City cityOne, City cityTwo, RouteColor color, int pointValue, int length) {
        this.groupId = groupId;
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.color = color;
        this.pointValue = pointValue;
        this.length = length;
        this.hasAdjacentRoute = false;
        this.adjacentRouteId = 0;
    }

    public Route(int groupId, City cityOne, City cityTwo, RouteColor color, int pointValue, int length, boolean hasAdjacentRoute, int adjacentRouteId) {
        this.groupId = groupId;
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.color = color;
        this.pointValue = pointValue;
        this.length = length;
        this.hasAdjacentRoute = hasAdjacentRoute;
        this.adjacentRouteId = adjacentRouteId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public City getCityOne() {
        return cityOne;
    }

    public void setCityOne(City cityOne) {
        this.cityOne = cityOne;
    }

    public City getCityTwo() {
        return cityTwo;
    }

    public void setCityTwo(City cityTwo) {
        this.cityTwo = cityTwo;
    }

    public RouteColor getColor() {
        return color;
    }

    public void setColor(RouteColor color) {
        this.color = color;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public int getAdjacentRouteId() {
        return adjacentRouteId;
    }

    public boolean isHasAdjacentRoute() {
        return hasAdjacentRoute;
    }

    public static Map<Integer, Route> ROUTE_GROUP_MAP;
    static {
        ROUTE_GROUP_MAP = new HashMap<Integer, Route>();
        ROUTE_GROUP_MAP.put(1, new Route(1, new City("Seattle"), new City("Calgary"), RouteColor.GRAY, 7, 4));
        ROUTE_GROUP_MAP.put(2, new Route(2, new City("San Francisco"), new City("Salt Lake City"), RouteColor.WHITE, 10, 5, true, 13));
        ROUTE_GROUP_MAP.put(3, new Route(3, new City("San Francisco"), new City("Portland"), RouteColor.GREEN, 10, 5, true, 14));
        ROUTE_GROUP_MAP.put(4, new Route(4, new City("Portland"), new City("Seattle"), RouteColor.GRAY, 1, 1, true, 7));
        ROUTE_GROUP_MAP.put(5, new Route(5, new City("Los Angeles"), new City("Las Vegas"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(6, new Route(6, new City("Vancouver"), new City("Calgary"), RouteColor.GRAY, 4, 3));
        ROUTE_GROUP_MAP.put(7, new Route(7, new City("Portland"), new City("Seattle"), RouteColor.GRAY, 1, 1, true, 4));
        ROUTE_GROUP_MAP.put(8, new Route(8, new City("Seattle"), new City("Vancouver"), RouteColor.GRAY, 1, 1, true, 9));
        ROUTE_GROUP_MAP.put(9, new Route(9, new City("Seattle"), new City("Vancouver"), RouteColor.GRAY, 1, 1, true, 8));
        ROUTE_GROUP_MAP.put(10, new Route(10, new City("Los Angeles"), new City("San Francisco"), RouteColor.YELLOW, 4, 3, true, 11));
        ROUTE_GROUP_MAP.put(11, new Route(11, new City("Los Angeles"), new City("San Francisco"), RouteColor.PINK, 4, 3, true, 10));
        ROUTE_GROUP_MAP.put(12, new Route(12, new City("Las Vegas"), new City("Salt Lake City"), RouteColor.ORANGE, 4, 3));
        ROUTE_GROUP_MAP.put(13, new Route(13, new City("San Francisco"), new City("Salt Lake City"), RouteColor.ORANGE, 10, 5, true, 2));
        ROUTE_GROUP_MAP.put(14, new Route(14, new City("San Francisco"), new City("Portland"), RouteColor.PINK, 10, 5, true, 3));
        ROUTE_GROUP_MAP.put(15, new Route(15, new City("Portland"), new City("Salt Lake City"), RouteColor.BLUE, 15, 6));
        ROUTE_GROUP_MAP.put(16, new Route(16, new City("Seattle"), new City("Helena"), RouteColor.YELLOW, 15, 6));
        ROUTE_GROUP_MAP.put(17, new Route(17, new City("Los Angeles"), new City("Phoenix"), RouteColor.GRAY, 4, 3));
        ROUTE_GROUP_MAP.put(18, new Route(18, new City("Calgary"), new City("Helena"), RouteColor.GRAY, 7, 4));
        ROUTE_GROUP_MAP.put(19, new Route(19, new City("Salt Lake City"), new City("Helena"), RouteColor.PINK, 4, 3));
        ROUTE_GROUP_MAP.put(20, new Route(20, new City("Salt Lake City"), new City("Denver"), RouteColor.RED, 4, 3, true, 20));
        ROUTE_GROUP_MAP.put(21, new Route(21, new City("Salt Lake City"), new City("Denver"), RouteColor.YELLOW, 4, 3, true, 19));
        ROUTE_GROUP_MAP.put(22, new Route(22, new City("Los Angeles"), new City("El Paso"), RouteColor.BLACK, 15, 6));
        ROUTE_GROUP_MAP.put(23, new Route(23, new City("Phoenix"), new City("El Paso"), RouteColor.GRAY, 4, 3));
        ROUTE_GROUP_MAP.put(24, new Route(24, new City("Phoenix"), new City("Denver"), RouteColor.WHITE, 10, 5));
        ROUTE_GROUP_MAP.put(25, new Route(25, new City("Phoenix"), new City("Santa Fe"), RouteColor.GRAY, 4, 3));
        ROUTE_GROUP_MAP.put(26, new Route(26, new City("Denver"), new City("Helena"), RouteColor.GREEN, 7, 4));
        ROUTE_GROUP_MAP.put(27, new Route(27, new City("Calgary"), new City("Winnipeg"), RouteColor.WHITE, 15, 6));
        ROUTE_GROUP_MAP.put(28, new Route(28, new City("Denver"), new City("Santa Fe"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(29, new Route(29, new City("Santa Fe"), new City("El Paso"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(30, new Route(30, new City("Helena"), new City("Winnipeg"), RouteColor.BLUE, 7, 4));
        ROUTE_GROUP_MAP.put(31, new Route(31, new City("Helena"), new City("Duluth"), RouteColor.ORANGE, 15, 6));
        ROUTE_GROUP_MAP.put(32, new Route(32, new City("Helena"), new City("Omaha"), RouteColor.RED, 10, 5));
        ROUTE_GROUP_MAP.put(33, new Route(33, new City("Denver"), new City("Omaha"), RouteColor.PINK, 7, 4));
        ROUTE_GROUP_MAP.put(34, new Route(34, new City("Winnipeg"), new City("Duluth"), RouteColor.BLACK, 7, 4));
        ROUTE_GROUP_MAP.put(35, new Route(35, new City("Santa Fe"), new City("Oklahoma City"), RouteColor.BLUE, 4, 3));
        ROUTE_GROUP_MAP.put(36, new Route(36, new City("Denver"), new City("Kansas City"), RouteColor.ORANGE, 7, 4, true, 37));
        ROUTE_GROUP_MAP.put(37, new Route(37, new City("Denver"), new City("Kansas City"), RouteColor.BLACK, 7, 4, true, 36));
        ROUTE_GROUP_MAP.put(38, new Route(38, new City("Denver"), new City("Oklahoma City"), RouteColor.RED, 7, 4));
        ROUTE_GROUP_MAP.put(39, new Route(39, new City("El Paso"), new City("Oklahoma City"), RouteColor.YELLOW, 10, 5));
        ROUTE_GROUP_MAP.put(40, new Route(40, new City("El Paso"), new City("Dallas"), RouteColor.RED, 7, 4));
        ROUTE_GROUP_MAP.put(41, new Route(41, new City("El Paso"), new City("Houston"), RouteColor.GREEN, 15, 6));
        ROUTE_GROUP_MAP.put(42, new Route(42, new City("Winnipeg"), new City("Sault Saint Marie"), RouteColor.GRAY, 15, 6));
        ROUTE_GROUP_MAP.put(43, new Route(43, new City("Duluth"), new City("Sault Saint Marie"), RouteColor.GRAY, 4, 3));
        ROUTE_GROUP_MAP.put(44, new Route(44, new City("Duluth"), new City("Omaha"), RouteColor.GRAY, 2, 2, true, 45));
        ROUTE_GROUP_MAP.put(45, new Route(45, new City("Duluth"), new City("Omaha"), RouteColor.GRAY, 2, 2, true, 44));
        ROUTE_GROUP_MAP.put(46, new Route(46, new City("Omaha"), new City("Kansas City"), RouteColor.GRAY, 1, 1, true, 47));
        ROUTE_GROUP_MAP.put(47, new Route(47, new City("Omaha"), new City("Kansas City"), RouteColor.GRAY, 1, 1, true, 46));
        ROUTE_GROUP_MAP.put(48, new Route(48, new City("Kansas City"), new City("Oklahoma City"), RouteColor.GRAY, 2, 2, true, 49));
        ROUTE_GROUP_MAP.put(49, new Route(49, new City("Kansas City"), new City("Oklahoma City"), RouteColor.GRAY, 2, 2, true, 48));
        ROUTE_GROUP_MAP.put(50, new Route(50, new City("Oklahoma City"), new City("Dallas"), RouteColor.GRAY, 2, 2, true, 51));
        ROUTE_GROUP_MAP.put(51, new Route(51, new City("Oklahoma City"), new City("Dallas"), RouteColor.GRAY, 2, 2, true, 50));
        ROUTE_GROUP_MAP.put(52, new Route(52, new City("Dallas"), new City("Houston"), RouteColor.GRAY, 1, 1, true, 53));
        ROUTE_GROUP_MAP.put(53, new Route(53, new City("Dallas"), new City("Houston"), RouteColor.GRAY, 1, 1, true, 52));
        ROUTE_GROUP_MAP.put(54, new Route(54, new City("Houston"), new City("New Orleans"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(55, new Route(55, new City("Dallas"), new City("Little Rock"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(56, new Route(56, new City("Oklahoma City"), new City("Little Rock"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(57, new Route(57, new City("Kansas City"), new City("Saint Louis"), RouteColor.BLUE, 2, 2, true, 58));
        ROUTE_GROUP_MAP.put(58, new Route(58, new City("Kansas City"), new City("Saint Louis"), RouteColor.PINK, 2, 2, true, 57));
        ROUTE_GROUP_MAP.put(59, new Route(59, new City("Omaha"), new City("Chicago"), RouteColor.BLUE, 7, 4));
        ROUTE_GROUP_MAP.put(60, new Route(60, new City("Duluth"), new City("Chicago"), RouteColor.RED, 4, 3));
        ROUTE_GROUP_MAP.put(61, new Route(61, new City("Duluth"), new City("Toronto"), RouteColor.PINK, 15, 6));
        ROUTE_GROUP_MAP.put(62, new Route(62, new City("Sault Saint Marie"), new City("Toronto"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(63, new Route(63, new City("Sault Saint Marie"), new City("Montreal"), RouteColor.BLACK, 10, 5));
        ROUTE_GROUP_MAP.put(64, new Route(64, new City("Pittsburgh"), new City("Raleigh"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(65, new Route(65, new City("Charleston"), new City("Miami"), RouteColor.PINK, 7, 4));
        ROUTE_GROUP_MAP.put(66, new Route(66, new City("Saint Louis"), new City("Chicago"), RouteColor.WHITE, 2, 2, true, 67));
        ROUTE_GROUP_MAP.put(67, new Route(67, new City("Saint Louis"), new City("Chicago"), RouteColor.GREEN, 2, 2, true, 66));
        ROUTE_GROUP_MAP.put(68, new Route(68, new City("New Orleans"), new City("Atlanta"), RouteColor.YELLOW, 7, 4, true, 69));
        ROUTE_GROUP_MAP.put(69, new Route(69, new City("New Orleans"), new City("Atlanta"), RouteColor.ORANGE, 7, 4, true, 68));
        ROUTE_GROUP_MAP.put(70, new Route(70, new City("Little Rock"), new City("Nashville"), RouteColor.WHITE, 4, 3));
        ROUTE_GROUP_MAP.put(71, new Route(71, new City("Little Rock"), new City("Saint Louis"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(72, new Route(72, new City("Pittsburgh"), new City("Washington"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(73, new Route(73, new City("New York"), new City("Washington"), RouteColor.ORANGE, 2, 2, true, 74));
        ROUTE_GROUP_MAP.put(74, new Route(74, new City("New York"), new City("Washington"), RouteColor.BLACK, 2, 2, true, 73));
        ROUTE_GROUP_MAP.put(75, new Route(75, new City("Montreal"), new City("New York"), RouteColor.BLUE, 4, 3));
        ROUTE_GROUP_MAP.put(76, new Route(76, new City("Montreal"), new City("Boston"), RouteColor.GRAY, 2, 2, true, 77));
        ROUTE_GROUP_MAP.put(77, new Route(77, new City("Montreal"), new City("Boston"), RouteColor.GRAY, 2, 2, true, 76));
        ROUTE_GROUP_MAP.put(78, new Route(78, new City("Pittsburgh"), new City("New York"), RouteColor.GREEN, 2, 2, true, 83));
        ROUTE_GROUP_MAP.put(79, new Route(79, new City("Raleigh"), new City("Washington"), RouteColor.GRAY, 2, 2, true, 82));
        ROUTE_GROUP_MAP.put(80, new Route(80, new City("Atlanta"), new City("Raleigh"), RouteColor.GRAY, 2, 2, true, 81));
        ROUTE_GROUP_MAP.put(81, new Route(81, new City("Atlanta"), new City("Raleigh"), RouteColor.GRAY, 2, 2, true, 80));
        ROUTE_GROUP_MAP.put(82, new Route(82, new City("Raleigh"), new City("Washington"), RouteColor.GRAY, 2, 2, true, 79));
        ROUTE_GROUP_MAP.put(83, new Route(83, new City("Pittsburgh"), new City("New York"), RouteColor.WHITE, 2, 2, true, 78));
        ROUTE_GROUP_MAP.put(84, new Route(84, new City("New Orleans"), new City("Little Rock"), RouteColor.GREEN, 4, 3));
        ROUTE_GROUP_MAP.put(85, new Route(85, new City("Toronto"), new City("Pittsburgh"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(86, new Route(86, new City("Chicago"), new City("Pittsburgh"), RouteColor.BLACK, 4, 3, true, 96));
        ROUTE_GROUP_MAP.put(87, new Route(87, new City("Nashville"), new City("Pittsburgh"), RouteColor.YELLOW, 7, 4));
        ROUTE_GROUP_MAP.put(88, new Route(88, new City("Nashville"), new City("Raleigh"), RouteColor.BLACK, 4, 3));
        ROUTE_GROUP_MAP.put(89, new Route(89, new City("Raleigh"), new City("Charleston"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(90, new Route(90, new City("Saint Louis"), new City("Pittsburgh"), RouteColor.GREEN, 10, 5));
        ROUTE_GROUP_MAP.put(91, new Route(91, new City("Saint Louis"), new City("Nashville"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(92, new Route(92, new City("Atlanta"), new City("Miami"), RouteColor.BLUE, 10, 5));
        ROUTE_GROUP_MAP.put(93, new Route(93, new City("New Orleans"), new City("Miami"), RouteColor.RED, 15, 6));
        ROUTE_GROUP_MAP.put(94, new Route(94, new City("Atlanta"), new City("Charleston"), RouteColor.GRAY, 2, 2));
        ROUTE_GROUP_MAP.put(95, new Route(95, new City("Nashville"), new City("Atlanta"), RouteColor.GRAY, 1, 1));
        ROUTE_GROUP_MAP.put(96, new Route(96, new City("Chicago"), new City("Pittsburgh"), RouteColor.ORANGE, 4, 3, true, 86));
        ROUTE_GROUP_MAP.put(97, new Route(97, new City("Boston"), new City("New York"), RouteColor.RED, 2, 2, true, 98));
        ROUTE_GROUP_MAP.put(98, new Route(98, new City("Boston"), new City("New York"), RouteColor.YELLOW, 2, 2, true, 97));
        ROUTE_GROUP_MAP.put(99, new Route(99, new City("Chicago"), new City("Toronto"), RouteColor.WHITE, 7, 4));
        ROUTE_GROUP_MAP.put(100, new Route(100, new City("Toronto"), new City("Montreal"), RouteColor.GRAY, 4, 3));
    }

    public enum RouteColor {
        PINK, WHITE, BLUE, YELLOW, ORANGE, BLACK, RED, GREEN, GRAY;
    }
}

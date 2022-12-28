package org.tau.utils;

import java.util.List;

public class GameConstants {
    public static final String START = "START";
    public static final String FINISH = "FINISH";
    public static final String CURRENT_POS_MARK = "X";
    public static final String NOT_VISITED = "_";
    public static final String VISITED = "#";
    public static final String FIRE = "F";
    public static final String RIVER = "R";
    public static final String HOLE = "H";
    public static final List<String> TRAPS = List.of(FIRE, RIVER, HOLE);
    public static final String UP = "W";
    public static final String DOWN = "S";
    public static final String LEFT = "A";
    public static final String RIGHT = "D";
}

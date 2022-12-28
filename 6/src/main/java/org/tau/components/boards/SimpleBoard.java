package org.tau.components.boards;

import org.tau.components.Board;
import org.tau.utils.GameConstants;

import java.util.Arrays;

public class SimpleBoard extends Board {
    public SimpleBoard(int numOfRowsAndCols) {
        super(numOfRowsAndCols);
        obstaclesPresent = false;
    }

    @Override
    protected void initTheBoard() {
        for (int i = 0; i < getArea().length; i++) {
            Arrays.fill(getArea()[i], GameConstants.NOT_VISITED);
        }
    }

    @Override
    public void printLegend() {
        System.out.println("=======================");
        System.out.println("LEGEND: " + GameConstants.CURRENT_POS_MARK + " - player's current position, " +
                GameConstants.NOT_VISITED + " - places the player did not visit, " +
                GameConstants.VISITED + " - places visited by the player, ");
        System.out.println();
        System.out.println("CONTROLS: UP - '" + GameConstants.UP + "', DOWN - '" + GameConstants.DOWN + "', " +
                "LEFT - '" + GameConstants.LEFT + "', RIGHT - '" + GameConstants.RIGHT + "', Quit the game - 'Q'");
        System.out.println("=======================");
    }
}

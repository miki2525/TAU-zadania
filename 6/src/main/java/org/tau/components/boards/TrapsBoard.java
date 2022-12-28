package org.tau.components.boards;

import org.tau.components.Board;
import org.tau.utils.GameConstants;

import java.util.Random;

public class TrapsBoard extends Board {
    public TrapsBoard(int numOfRowsAndCols) {
        super(numOfRowsAndCols);
    }

    @Override
    protected void initTheBoard() {
        Random randomBoard = new Random();
        Random randomTraps = new Random();
        for (int i = 0; i < getArea().length; i++) {
            for (int j = 0; j < getArea()[i].length; j++) {
                int randomBoardIndex = randomBoard.nextInt(0, getArea()[getArea().length].length);
                int randomTrapIndex = randomTraps.nextInt(0, GameConstants.TRAPS.size());
                while (i == 0 && j == 0 && randomBoardIndex == 0) {
                    randomBoardIndex = randomBoard.nextInt(0, getArea()[getArea().length].length);
                }
                while (i == getArea().length - 1 && j == getArea()[i].length - 1 && randomBoardIndex == getArea()[i].length - 1) {
                    randomBoardIndex = randomBoard.nextInt(0, getArea()[getArea().length].length);
                }
                if (randomBoardIndex == j) {
                    getArea()[i][j] = GameConstants.TRAPS.get(randomTrapIndex);
                }
                else {
                    getArea()[i][j] = GameConstants.NOT_VISITED;
                }
            }
        }
    }

    @Override
    public void printLegend() {
        System.out.println("=======================");
        System.out.print("LEGEND: "+ GameConstants.CURRENT_POS_MARK+" - player's current position, " +
                GameConstants.NOT_VISITED+" - places the player did not visit, " +
                GameConstants.VISITED+" - places visited by the player");
        GameConstants.TRAPS.forEach(trap -> System.out.print(trap + " "));
        System.out.println("- obstacles, can't jump over");
        System.out.println();
        System.out.println("CONTROLS: UP - '"+GameConstants.UP+"', DOWN - '"+GameConstants.DOWN+"', " +
                "LEFT - '"+GameConstants.LEFT+"', RIGHT - '"+GameConstants.RIGHT+"', Quit the game - 'Q'");
        System.out.println("=======================");
    }
}

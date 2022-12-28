package org.tau.components.boards;

import org.tau.components.Board;
import org.tau.utils.GameConstants;

import java.util.Arrays;
import java.util.Random;

public class TrapsBoard extends Board {
    public TrapsBoard(int numOfRowsAndCols) {
        super(numOfRowsAndCols);
        obstaclesPresent = true;
    }

    @Override
    protected void initTheBoard() {
        for (int i = 0; i < getArea().length; i++) {
            Arrays.fill(getArea()[i], GameConstants.NOT_VISITED);
        }
        for (int i = 0; i < getArea().length; i++) {
            Random randomBoard = new Random();
            Random randomTraps = new Random();
            int randomBoardIndex = randomBoard.nextInt(0, getArea()[getArea().length - 1].length);
            int randomTrapIndex = randomTraps.nextInt(0, GameConstants.TRAPS.size());
            for (int j = 0; j < getArea()[i].length; j++) {
                if (randomBoardIndex == j && !GameConstants.TRAPS.contains(getArea()[i][j])) {
                    while (blockThePath(i, randomBoardIndex)) {
                        randomBoardIndex = randomBoard.nextInt(0, getArea()[getArea().length - 1].length);
                    }
                    getArea()[i][randomBoardIndex] = GameConstants.TRAPS.get(randomTrapIndex);
                }
            }
        }
    }

    private boolean blockThePath(int currentRow, int randomCol) {
        String[][] board = getArea();
        int rowLen = board.length;
        int colLen = board[0].length;
        if ((GameConstants.TRAPS.contains(board[rowLen - 1][colLen - 2]) && currentRow == rowLen - 2 && randomCol == colLen - 1) ||
                (GameConstants.TRAPS.contains(board[rowLen - 2][colLen - 1]) && currentRow == rowLen - 1 && randomCol == colLen - 2)) {
            return true;
        }
        if ((GameConstants.TRAPS.contains(board[1][0]) && currentRow == 0 && randomCol == 1) ||
                (GameConstants.TRAPS.contains(board[0][1]) && currentRow == 1 && randomCol == 0)){
            return true;
        }
        if (currentRow == 0 && randomCol == 0){
            return true;
        }
        if (currentRow == rowLen - 1 && randomCol == colLen - 1){
            return true;
        }
        //TODO add more cases
        return false;
    }

    @Override
    public void printLegend() {
        System.out.println("=======================");
        System.out.print("LEGEND: " + GameConstants.CURRENT_POS_MARK + " - player's current position, " +
                GameConstants.NOT_VISITED + " - places the player did not visit, " +
                GameConstants.VISITED + " - places visited by the player, ");
        GameConstants.TRAPS.forEach(trap -> System.out.print(trap + " "));
        System.out.println("- obstacles, can't jump over");
        System.out.println();
        System.out.println("CONTROLS: UP - '" + GameConstants.UP + "', DOWN - '" + GameConstants.DOWN + "', " +
                "LEFT - '" + GameConstants.LEFT + "', RIGHT - '" + GameConstants.RIGHT + "', Quit the game - 'Q'");
        System.out.println("=======================");
    }
}

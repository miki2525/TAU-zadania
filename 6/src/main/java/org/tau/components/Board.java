package org.tau.components;

import org.tau.utils.GameConstants;

public abstract class Board {
    private final String[][] area;
    protected boolean obstaclesPresent;

    public Board(int numOfRowsAndCols) {
        this.area = new String[numOfRowsAndCols][numOfRowsAndCols];
        initTheBoard();
        initStartFinish(numOfRowsAndCols, numOfRowsAndCols);
    }

    protected String[][] getArea() {
        return area;
    }

    protected abstract void initTheBoard();

    public abstract void printLegend();

    public boolean isObstaclesPresent() {
        return obstaclesPresent;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < area[0].length; i++) {
            int colNum = i + 1;
            if (i == 0) {
                System.out.printf("%4s", colNum);
            } else {
                System.out.printf("%10s", colNum);
            }
        }
        System.out.println();
        for (int i = 0; i < area.length; i++) {
            System.out.printf("%-5s", i + 1);
            for (int j = 0; j < area[i].length; j++) {
                if (j + 1 == area[i].length) {
                    System.out.println(area[i][j]);
                } else {
                    System.out.printf("%-10s", area[i][j]);
                }
            }
        }
    }

    private void initStartFinish(int numOfRows, int numOfCols) {
        area[0][0] = GameConstants.START;
        area[numOfRows - 1][numOfCols - 1] = GameConstants.FINISH;
    }
}

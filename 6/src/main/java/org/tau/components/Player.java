package org.tau.components;

import org.tau.utils.EndOfMapException;
import org.tau.utils.GameConstants;
import org.tau.utils.InvalidInputsException;
import org.tau.utils.ObstacleException;

public class Player {

    private String nickName;
    private Position pos;
    private boolean finished;
    private Board board;

    public Player(String nickName, Board board) {
        this.nickName = nickName;
        this.board = board;
        pos = new Position();
        markTheCurrentPosition(pos.getRow(), pos.getCol());
    }

    public void move(char direction) {
        direction = Character.toUpperCase(direction);
        int currentRow = pos.getRow();
        int currentCol = pos.getCol();
        switch (direction) {
            case 'W' -> {
                if (currentRow == 0) {
                    throw new EndOfMapException("Reach the end of the map. Can't move up");
                }
                obstacleOccured(currentRow, currentCol);
                markThePreviousPosition(currentRow, currentCol);
                --currentRow;
                pos.setRow(currentRow);
            }
            case 'A' -> {
                if (currentCol == 0) {
                    throw new EndOfMapException("Reach the end of the map. Can't move left");
                }
                obstacleOccured(currentRow, currentCol);
                markThePreviousPosition(currentRow, currentCol);
                --currentCol;
                pos.setCol(currentCol);
            }
            case 'D' -> {
                if (currentCol == board.getArea()[currentRow].length - 1) {
                    throw new EndOfMapException("Reach the end of the map. Can't move right");
                }
                obstacleOccured(currentRow, currentCol);
                markThePreviousPosition(currentRow, currentCol);
                ++currentCol;
                pos.setCol(currentCol);
            }
            case 'S' -> {
                if (currentRow == board.getArea().length - 1) {
                    throw new EndOfMapException("Reach the end of the map. Can't move down");
                }
                obstacleOccured(currentRow, currentCol);
                markThePreviousPosition(currentRow, currentCol);
                ++currentRow;
                pos.setRow(currentRow);
            }
            default ->
                    throw new InvalidInputsException("INVALID INPUT! Only ['" + GameConstants.UP + "', '" + GameConstants.DOWN + "', " +
                            "'" + GameConstants.LEFT + "', '" + GameConstants.RIGHT + "'] are valid");
        }

        if (reachTheFINISH(currentRow, currentCol)) return;
        resetStartFinish(currentRow, currentCol);
        markTheCurrentPosition(currentRow, currentCol);
    }

    private void resetStartFinish(int currentRow, int currentCol) {
        resetSTART(currentRow, currentCol);
        resetFINISH(currentRow, currentCol);
    }

    private void resetFINISH(int currentRow, int currentCol) {
        int lastColIndex = board.getArea()[board.getArea().length - 1].length - 1;
        if (!board.getArea()[board.getArea().length - 1][lastColIndex].equals(GameConstants.FINISH)
                && (currentRow != board.getArea().length - 1 || currentCol != lastColIndex)) {
            board.getArea()[board.getArea().length - 1][lastColIndex] = GameConstants.FINISH;
        }
    }

    private void resetSTART(int currentRow, int currentCol) {
        if (!board.getArea()[0][0].equals(GameConstants.START)
                && (currentRow != 0 || currentCol != 0)) {
            board.getArea()[0][0] = GameConstants.START;
        }
    }

    private void markThePreviousPosition(int currentRow, int currentCol) {
        if (!board.getArea()[currentRow][currentCol].equals(GameConstants.VISITED)) {
            board.getArea()[currentRow][currentCol] = GameConstants.VISITED;
        }
    }

    private void markTheCurrentPosition(int currentRow, int currentCol) {
        if ((currentRow != 0 || currentCol != 0)
                && (currentRow != board.getArea().length - 1 || currentCol != board.getArea()[board.getArea().length - 1].length - 1)) {
            board.getArea()[currentRow][currentCol] = GameConstants.CURRENT_POS_MARK;
        } else {
            board.getArea()[currentRow][currentCol] = board.getArea()[currentRow][currentCol] + "[" + GameConstants.CURRENT_POS_MARK + "]";
        }
    }

    private void obstacleOccured(int currentRow, int currentCol) {
        if (GameConstants.TRAPS.contains(board.getArea()[currentRow][currentCol])) {
            throw new ObstacleException("Obstacle occured. Can't move higher");
        }
    }

    private boolean reachTheFINISH(int currentRow, int currentCol) {
        if (board.getArea()[currentRow][currentCol].equals(GameConstants.FINISH)) {
            finished = true;
            return true;
        }
        return false;
    }

    public Position currentPosition() {
        return pos;
    }

    public String getNickName() {
        return nickName;
    }

    public boolean isFinished() {
        return finished;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

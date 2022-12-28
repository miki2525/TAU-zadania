package org.tau.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tau.components.boards.SimpleBoard;
import org.tau.components.boards.TrapsBoard;
import org.tau.utils.EndOfMapException;
import org.tau.utils.GameConstants;
import org.tau.utils.InvalidInputsException;
import org.tau.utils.ObstacleException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Board board;
    private Player player;
    private int lastRowIndex;
    private int lastColIndex;

    @BeforeEach
    public void setUp() {
        int dimension = 10;
        board = new SimpleBoard(dimension);
        lastRowIndex = board.getArea().length - 1;
        lastColIndex = board.getArea()[lastRowIndex].length - 1;
        player = new Player("playerTest", board);
    }

    @Test
    public void checkSTARTPosition() {
        assertTrue(board.getArea()[0][0].contains(GameConstants.START));
    }

    @Test
    public void checkFINISHPosition() {
        assertEquals(GameConstants.FINISH, board.getArea()[lastRowIndex][lastColIndex]);
    }

    @Test
    public void moveRightUntilTheEdge() {
        for (int i = 0; i < board.getArea().length; i++) {
            assertTrue(board.getArea()[0][i].contains(GameConstants.CURRENT_POS_MARK));
            if (i == board.getArea().length - 1) {
                EndOfMapException ex = assertThrows(EndOfMapException.class,
                        () -> player.move(GameConstants.RIGHT.charAt(0)));
                assertEquals("Reach the end of the map. Can't move right", ex.getMessage());
            } else {
                player.move(GameConstants.RIGHT.charAt(0));
            }
        }
    }

    @Test
    public void moveLeftUntilTheEdge() {
        EndOfMapException ex = assertThrows(EndOfMapException.class,
                () -> player.move(GameConstants.LEFT.charAt(0)));
        assertEquals("Reach the end of the map. Can't move left", ex.getMessage());
    }

    @Test
    public void moveUpUntilTheEdge() {
       EndOfMapException ex = assertThrows(EndOfMapException.class,
                () -> player.move(GameConstants.UP.charAt(0)));
        assertEquals("Reach the end of the map. Can't move up", ex.getMessage());
    }

    @Test
    public void moveDownUntilTheEdge() {
        for (int i = 0; i < board.getArea().length; i++) {
            assertTrue(board.getArea()[i][0].contains(GameConstants.CURRENT_POS_MARK));
            if (i == board.getArea().length - 1) {
                EndOfMapException ex = assertThrows(EndOfMapException.class,
                        () -> player.move(GameConstants.DOWN.charAt(0)));
                assertEquals("Reach the end of the map. Can't move down", ex.getMessage());
            } else {
                player.move(GameConstants.DOWN.charAt(0));
            }
        }
    }

    @Test
    public void performFullPathFromStartToFinish() {
        int dim = 5;
        Board board2 = new SimpleBoard(dim);
        player.setBoard(board2);
        assertTrue(board2.getArea()[0][0].contains(GameConstants.START + "[" + GameConstants.CURRENT_POS_MARK + "]"));
        for (int i = 0; i < board2.getArea().length - 1; i++) {
            player.move(GameConstants.RIGHT.charAt(0));
            player.move(GameConstants.DOWN.charAt(0));
        }
        assertEquals(board2.getArea()[0][1], GameConstants.VISITED);
        assertEquals(board2.getArea()[1][1], GameConstants.VISITED);
        assertEquals(board2.getArea()[dim - 2][dim - 2], GameConstants.VISITED);
        assertEquals(board2.getArea()[dim - 2][dim - 1], GameConstants.VISITED);
    }

    @Test
    public void throwInvalidInputException() {
        InvalidInputsException ex = assertThrows(InvalidInputsException.class,
                () -> player.move('*'));
        assertEquals("INVALID INPUT! Only ['" + GameConstants.UP + "', '" + GameConstants.DOWN + "', " +
                "'" + GameConstants.LEFT + "', '" + GameConstants.RIGHT + "'] are valid", ex.getMessage());
    }

    @Test
    public void faceTheObstacleThrowObstacleException() {
        int dim = 5;
        Board board2 = new TrapsBoard(dim);
        player.setBoard(board2);
        //traps appears in every row, so we can just move in one direction until we run into them
        for (int i = 0; i < board2.getArea().length; i++) {
            assertTrue(board2.getArea()[0][i].contains(GameConstants.CURRENT_POS_MARK));
            if (GameConstants.TRAPS.contains(board2.getArea()[0][i + 1])) {
                ObstacleException ex = assertThrows(ObstacleException.class,
                        () -> player.move(GameConstants.RIGHT.charAt(0)));
                assertEquals("Obstacle occured. Can't move here", ex.getMessage());
                break;
            } else {
                player.move(GameConstants.RIGHT.charAt(0));
            }
        }
    }
}